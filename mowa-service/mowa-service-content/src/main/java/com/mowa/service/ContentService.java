package com.mowa.service;

import com.github.pagehelper.PageInfo;
import com.mowa.content.pojo.Content;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface ContentService {

    /***
     * Content多条件分页查询
     * @param content
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(Content content, int page, int size);

    /***
     * Content分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(int page, int size);

    /***
     * Content多条件搜索方法
     * @param content
     * @return
     */
    List<Content> findList(Content content);

    /***
     * 删除Content
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Content数据
     * @param content
     */
    void update(Content content);

    /***
     * 新增Content
     * @param content
     */
    void add(Content content);

    /**
     * 根据ID查询Content
     * @param id
     * @return
     */
     Content findById(Integer id);

    /***
     * 查询所有Content
     * @return
     */
    List<Content> findAll();
}
