package com.mowa.service;

import com.github.pagehelper.PageInfo;
import com.mowa.goods.pojo.Brand;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 0:41
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface BrandService {

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    int save(Brand brand);

    /**
     * 修改品牌
     */
    int update(Brand brand);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查询
     */
    Brand findById(int id);

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 条件查询
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每页显示多少条
     * @return
     */
    PageInfo <Brand> findPage(int page,int size);

    /**
     * 条件加分页查询
     * @param page 当前页
     * @param size 每页显示的条数
     * @param brand
     * @return
     */
    PageInfo <Brand> findPage(int page,int size,Brand brand);
}
