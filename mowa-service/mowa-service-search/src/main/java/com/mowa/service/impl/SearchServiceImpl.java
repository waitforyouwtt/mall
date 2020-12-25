package com.mowa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mowa.Result;
import com.mowa.constant.Constant;
import com.mowa.feign.SkuFeign;
import com.mowa.goods.pojo.Sku;
import com.mowa.page.ElasticSearchUtils;
import com.mowa.search.pojo.SkuInfo;
import com.mowa.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SkuFeign skuFeign;
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ElasticSearchUtils searchUtils;

    /**
     * 查询需要同步es的数据
     *
     * @return
     */
    @Override
    public List<SkuInfo> skuInfos() {
        Result<List<Sku>> querySkuResult = skuFeign.findAll();
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(querySkuResult.getData()),SkuInfo.class);
        if (CollectionUtils.isEmpty(skuInfos)){
            log.info("没有数据推送elasticsearch......");
            return Collections.EMPTY_LIST;
        }
        return skuInfos;
    }

    @Override
    public void importSkuList() {
        //测试环境，只需要3条即可
        List<SkuInfo> skuInfos = this.skuInfos().stream().sorted( Comparator.comparing(SkuInfo::getId)).limit(3).collect( Collectors.toList());
        //分段处理，防止数据过多
        List<List<SkuInfo>> parts = Lists.partition(skuInfos, 50);
        for(List<SkuInfo> list : parts) {
           for (SkuInfo skuinfo: list){
               //获取spec -》map(string)->map类型{'颜色': '紫色', '尺码': '250度'}
               Map<String,Object> specMap = JSON.parseObject( skuinfo.getSpec(),Map.class );
               //如果需要需要生成动态的域，只需要将该域存入到一个Map<Stirng,Object>对象中即可，该Map<String,Object>的key会生成一个域，域的名字为该map的key
               //当前Map<String,Object>后面Object的值会作为当前sku对象该域(key)对应的值
               skuinfo.setSpecMap( specMap );
               //文档内容
               Map<String,Object> jsonMap = JSONObject.parseObject(JSON.toJSONString(skuinfo));
               searchUtils.save( Constant.sku_index,Constant.sku_index_type,jsonMap );
           }
        }
    }

    @Override
    public void importSkuList2() {
        List<SkuInfo> skuInfos = this.skuInfos().stream().sorted( Comparator.comparing(SkuInfo::getId)).limit(1).collect( Collectors.toList());
        //分段处理，防止数据过多
        List<List<SkuInfo>> parts = Lists.partition(skuInfos, 50);
        for(List<SkuInfo> list : parts) {
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add();
            bulkRequest.timeout("50s");
            for (int i = 0; i < list.size(); i++) {
                bulkRequest.add(new IndexRequest( Constant.sku_index).id(list.get(i).getId().toString())
                        .source(JSON.toJSONString(list.get(i)), XContentType.JSON));
            }
            try {
                BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                log.info("推送es 返回结果：{}",bulkResponse);
            } catch (IOException e) {
                log.error("推送es 数据异常：{}",e.getMessage());
            }
        }
    }

    /**
     * 根据id 查询文档数据
     *
     * @param id
     */
    @Override
    public SkuInfo queryDocumentById(String id) {
        //得到文档的内容
        Map<String, Object> dataSource = searchUtils.queryById3(Constant.sku_index,Constant.sku_index_type, id );
        SkuInfo skuInfo = JSON.parseObject( JSON.toJSONString( dataSource ), SkuInfo.class );
        return skuInfo;
    }

    /**
     * 查询sku信息
     *
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        return null;
    }
}
