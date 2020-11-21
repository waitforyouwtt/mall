package com.mowa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mowa.dao.BrandMapper;
import com.mowa.goods.pojo.Brand;
import com.mowa.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 0:41
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Resource
    BrandMapper brandMapper;


    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    @Override
    public int save(Brand brand) {
        return brandMapper.insertSelective( brand );
    }

    /**
     * 修改品牌
     *
     * @param brand
     */
    @Override
    public int update(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective( brand );
    }

    /**
     * 删除品牌
     *
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        return brandMapper.deleteByPrimaryKey( id );
    }

    /**
     * 根据id查询
     *
     * @param id
     */
    @Override
    public Brand findById(int id) {
        return brandMapper.selectByPrimaryKey( id );
    }

    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 条件查询
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        return brandMapper.selectByExample( createExample(brand) );
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每页显示多少条
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        PageHelper.startPage( page,size );
        return new PageInfo<>( brandMapper.selectAll()) ;
    }

    /**
     * 条件加分页查询
     *
     * @param page
     * @param size
     * @param brand
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size, Brand brand) {
        PageHelper.startPage( page,size );
        Example example = createExample(brand);
        return new PageInfo<>( brandMapper.selectByExample( example ) );
    }

    protected Example createExample(Brand brand){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null){
            if (StringUtils.isNotBlank(brand.getName())){
                //列名 & 属性名
                criteria.andLike( "name","%"+brand.getName()+"%" );
            }
            if (StringUtils.isNotBlank( brand.getLetter() )){
                criteria.andEqualTo( "letter",brand.getLetter() );
            }
        }
        return example;
    }
}
