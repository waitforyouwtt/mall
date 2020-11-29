package com.mowa.dao;

import com.mowa.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 0:40
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface BrandMapper extends Mapper<Brand> {

    @Select( "select tb.* from tb_brand tb,tb_category_brand tcb where tb.id=tcb.brand_id and tcb.category_id = #{categoryId}" )
    List<Brand> findByCategoryId(int categoryId);
}
