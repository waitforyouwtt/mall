package com.mowa.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mowa.Result;
import com.mowa.feign.SkuFeign;
import com.mowa.goods.pojo.Sku;
import com.mowa.search.pojo.SkuInfo;
import com.mowa.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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

    @Override
    public void importSkuList() {
        Result<List<Sku>> querySkuResult = skuFeign.findAll();
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(querySkuResult.getData()),SkuInfo.class);
        if (CollectionUtils.isEmpty(skuInfos)){
            log.info("没有数据推送elasticsearch......");
            return;
        }
        //分段处理，防止数据过多
        List<List<SkuInfo>> parts = Lists.partition(skuInfos, 50);
        for(List<SkuInfo> list : parts) {
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add();
            bulkRequest.timeout("50s");
            for (int i = 0; i < list.size(); i++) {
                //批量更新删除
                bulkRequest.add(new IndexRequest("skuinfo").id(list.get(i).getId().toString())
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
}
