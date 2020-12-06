package com.mowa.service;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
public interface SearchService {

    /**
     * 全量从数据库导入到es
     */
    void importSkuList();
}
