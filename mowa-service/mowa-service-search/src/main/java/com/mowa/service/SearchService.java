package com.mowa.service;

import com.mowa.search.pojo.SkuInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
public interface SearchService {

    /**
     * 查询需要同步es的数据
     * @return
     */
    List<SkuInfo> skuInfos();
    /**
     * 全量从数据库导入到es
     */
    void importSkuList();

    /**
     * 全量从数据库导入到es
     */
    void importSkuList2();

    /**
     * 根据id查询文档数据
     * @param id
     * @return
     */
    SkuInfo queryDocumentById(String id);

    /**
     * 查询sku信息
     * @param searchMap
     * @return
     */
    Map<String,Object> search(Map<String,String> searchMap);

}
