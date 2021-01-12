package com.mowa.service;

import com.github.pagehelper.PageInfo;
import com.mowa.user.pojo.UserInfo;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface UserInfoService {

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserInfo> findPage(UserInfo user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserInfo> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<UserInfo> findList(UserInfo user);

    /***
     * 删除User
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改User数据
     * @param user
     */
    void update(UserInfo user);

    /***
     * 新增User
     * @param user
     */
    void add(UserInfo user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    UserInfo findById(Integer id);

    /***
     * 查询所有User
     * @return
     */
    List<UserInfo> findAll();
}
