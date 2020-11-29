package com.mowa.service;

import com.github.pagehelper.PageInfo;
import com.mowa.goods.pojo.Spu;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Integer id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     * 审核商品
     */
    void audit(String spuId);

    /**
     * 上架商品
     */
    void put(String spuId);

    /**
     * 下架商品
     */
    void pull(String spuId);

    /**
     * 上架商品
     */
    void putMany(String[] spuIds);
}
