package com.mowa.page;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.handler.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/12/12 14:40
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class ElasticSearchUtils {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 添加document数据
     * @param index
     * @param type
     * @param jsonMap
     */
    public void save(String index,String type,Map<String,Object> jsonMap){
        log.info( "添加新的document数据请求参数：{}", JSONUtil.toJsonStr( jsonMap ) );
        if(MapUtil.isEmpty( jsonMap )){
            throw new BusinessException( StatusCodeEnum.PARAMS_ERROR.getCode(),"请求参数不能为空" );
        }
        //创建索引对象:如果id 不为空，则使用传参id,如果为空，则使用elasticsearch 自动生成的id
        IndexRequest indexRequest = null;
        if (jsonMap.get( "id" ) == null){
            indexRequest = new IndexRequest(index, type);
        }else{
            indexRequest = new IndexRequest(index, type,jsonMap.get( "id" )+"");
        }
        //文档内容
        indexRequest.source(jsonMap);
        //通过client进行http的请求
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("添加document数据异常信息是：{}",e.getMessage());
        }
        DocWriteResponse.Result result = indexResponse.getResult();
        log.info("添加document数据的结果是：{}", JSON.toJSONString(result));
    }

    /**
     * 操作document数据[指定index,type,id]：
     * 1.如果索引库中不存在该数据，则插入一条数据;
     * 2.如果索引库中已存在该数据，则更新指定数据.
     * @param index
     * @param type
     * @param jsonMap
     */
    public void saveOrUpdate(String index,String type,String id,Map<String,Object> jsonMap){
        log.info( "操作document数据请求参数:{}", JSONUtil.toJsonStr(jsonMap) );
        //BeanMap.create(entity),可以将对象转换为map
        //IndexRequest indexRequest = new IndexRequest(index).type(type).id(String.valueOf(id)).source(BeanMap.create(person));
        IndexRequest indexRequest = new IndexRequest(index).type(type).id(String.valueOf(id)).source(jsonMap);
        //UpdateRequest updateRequest = new UpdateRequest(index, type, String.valueOf(id)).doc(BeanMap.create(person)).upsert(indexRequest);
        UpdateRequest updateRequest = new UpdateRequest(index, type, String.valueOf(id)).doc(jsonMap).upsert(indexRequest);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(updateRequest);
        BulkResponse bulk = null;
        try {
            bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
           log.error( "操作document数据异常信息:{}",e.getMessage() );
        }
        log.info("操作document数据的结果：{}",bulk);
    }

    /**
     * 操作document数据[指定index,type,id]：
     * 1.如果索引库中不存在该数据，则插入一条数据;
     * 2.如果索引库中已存在该数据，则更新指定数据.
     * @param index
     * @param type
     * @param jsonMap
     */
    public void saveOrUpdate2(String index,String type,Map<String,Object> jsonMap){
        log.info( "操作document数据请求参数:{}",JSONUtil.toJsonStr( jsonMap ) );
        //Map<String,Object> jsonMap = JSONObject.parseObject(JSON.toJSONString(entity));
        IndexRequest indexRequest = new IndexRequest(index,type).source(jsonMap);
        UpdateRequest updateRequest = new UpdateRequest(index, type, String.valueOf(jsonMap.get( "id" ))).doc(jsonMap).upsert(indexRequest);
        UpdateResponse update = null;
        try {
            update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error( "操作document数据异常信息:{}",e.getMessage() );
        }
        DocWriteResponse.Result result = update.getResult();
        log.info("操作document数据的结果：{}",result);
    }

    /**
     * 根据id查询elasticsearch
     * @param index
     * @param type
     * @param id
     * @return
     */
    public Map<String, Object> queryById1(String index, String type,String id) {
        log.info( "根据id查询elasticsearch请求参数：{}", id );
        if (StringUtils.isBlank( id )) {
            throw new BusinessException( StatusCodeEnum.PARAMS_ERROR.getCode(), "请求参数不能为空" );
        }

        Map<String, Object> dataSource = new HashMap<>( 16 );
        GetRequest getRequest = new GetRequest( index, type, id );
        try {
            GetResponse getResponse = restHighLevelClient.get( getRequest, RequestOptions.DEFAULT );
            //得到文档的内容
            dataSource = getResponse.getSourceAsMap();
        } catch (Exception e) {
            log.error( "根据id查询elasticsearch异常信息：{}", e.getMessage() );
        }
        return dataSource;
    }

    public Map<String, Object> queryById2(String index, String type,String id) {
        log.info( "根据id查询elasticsearch请求参数：{}", id );
        if (StringUtils.isBlank( id )) {
            throw new BusinessException( StatusCodeEnum.PARAMS_ERROR.getCode(), "请求参数不能为空" );
        }
        Map<String, Object> dataSource = new HashMap<>( 16 );

        //搜索请求对象 & 指定类型
        SearchRequest searchRequest = new SearchRequest(index).types( type );
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String[] ids = new String[]{id};
        searchSourceBuilder.query(QueryBuilders.termsQuery("_id",ids));

        //向搜索请求对象中设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索,向ES发起http请求
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("通过Id查询projectInfoES索引库发生异常：{}",e.getMessage());
        }
        //搜索结果
        SearchHits hits = searchResponse.getHits();
        //得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit:searchHits){
            dataSource = hit.getSourceAsMap();
        }
        return dataSource;
    }

    public Map<String, Object> queryById3(String index, String type,String id) {
        log.info( "根据id查询elasticsearch请求参数：{}", id );
        if (StringUtils.isBlank( id )) {
            throw new BusinessException( StatusCodeEnum.PARAMS_ERROR.getCode(), "请求参数不能为空" );
        }
        Map<String, Object> dataSource = new HashMap<>( 16 );

        //搜索请求对象 & 指定类型
        SearchRequest searchRequest = new SearchRequest(index).types( type );
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //搜索方式
        //MatchQuery
        searchSourceBuilder.query(QueryBuilders.matchQuery("id",id).minimumShouldMatch("80%"));

        //向搜索请求对象中设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索,向ES发起http请求
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("通过database Id查询projectInfoES索引库发生异常：{}",e.getMessage());
        }
        //搜索结果
        SearchHits hits = searchResponse.getHits();
        //得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();

        for(SearchHit hit:searchHits){
            dataSource = hit.getSourceAsMap();
        }
        return dataSource;
    }

}
