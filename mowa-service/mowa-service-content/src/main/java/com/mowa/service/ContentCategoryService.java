package com.mowa.service;

import com.github.pagehelper.PageInfo;
import com.mowa.content.pojo.ContentCategory;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface ContentCategoryService {

    /***
     * ContentCategory多条件分页查询
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int page, int size);

    /***
     * ContentCategory分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(int page, int size);

    /***
     * ContentCategory多条件搜索方法
     * @param contentCategory
     * @return
     */
    List<ContentCategory> findList(ContentCategory contentCategory);

    /***
     * 删除ContentCategory
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改ContentCategory数据
     * @param contentCategory
     */
    void update(ContentCategory contentCategory);

    /***
     * 新增ContentCategory
     * @param contentCategory
     */
    void add(ContentCategory contentCategory);

    /**
     * 根据ID查询ContentCategory
     * @param id
     * @return
     */
     ContentCategory findById(Integer id);

    /***
     * 查询所有ContentCategory
     * @return
     */
    List<ContentCategory> findAll();
}
