package com.mowa.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mowa.dao.BrandMapper;
import com.mowa.dao.CategoryMapper;
import com.mowa.dao.SkuMapper;
import com.mowa.dao.SpuMapper;
import com.mowa.goods.pojo.*;
import com.mowa.service.SkuService;
import com.mowa.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;

    @Resource
    SpuMapper spuMapper;

    @Resource
    CategoryMapper categoryMapper ;

    @Resource
    BrandMapper brandMapper;


    /**
     * Sku条件+分页查询
     * @param sku 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Sku> findPage(Sku sku, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(sku);
        //执行搜索
        return new PageInfo<Sku>(skuMapper.selectByExample(example));
    }

    /**
     * Sku分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Sku> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Sku>(skuMapper.selectAll());
    }

    /**
     * Sku条件查询
     * @param sku
     * @return
     */
    @Override
    public List<Sku> findList(Sku sku){
        //构建查询条件
        Example example = createExample(sku);
        //根据构建的条件查询数据
        return skuMapper.selectByExample(example);
    }


    /**
     * Sku构建查询对象
     * @param sku
     * @return
     */
    public Example createExample(Sku sku){
        Example example=new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        if(sku!=null){
            // 自增主键
            if(!StringUtils.isEmpty(sku.getId())){
                    criteria.andEqualTo("id",sku.getId());
            }
            // 商品条码
            if(!StringUtils.isEmpty(sku.getSn())){
                    criteria.andEqualTo("sn",sku.getSn());
            }
            // SKU名称
            if(!StringUtils.isEmpty(sku.getName())){
                    criteria.andLike("name","%"+sku.getName()+"%");
            }
            // 价格（分）
            if(!StringUtils.isEmpty(sku.getPrice())){
                    criteria.andEqualTo("price",sku.getPrice());
            }
            // 库存数量
            if(!StringUtils.isEmpty(sku.getNum())){
                    criteria.andEqualTo("num",sku.getNum());
            }
            // 库存预警数量
            if(!StringUtils.isEmpty(sku.getAlertNum())){
                    criteria.andEqualTo("alertNum",sku.getAlertNum());
            }
            // 商品图片
            if(!StringUtils.isEmpty(sku.getImage())){
                    criteria.andEqualTo("image",sku.getImage());
            }
            // 商品图片列表
            if(!StringUtils.isEmpty(sku.getImages())){
                    criteria.andEqualTo("images",sku.getImages());
            }
            // 重量（克）
            if(!StringUtils.isEmpty(sku.getWeight())){
                    criteria.andEqualTo("weight",sku.getWeight());
            }
            // 创建时间
            if(!StringUtils.isEmpty(sku.getCreateTime())){
                    criteria.andEqualTo("createTime",sku.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(sku.getUpdateTime())){
                    criteria.andEqualTo("updateTime",sku.getUpdateTime());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(sku.getSpuId())){
                    criteria.andEqualTo("spuId",sku.getSpuId());
            }
            // 类目ID
            if(!StringUtils.isEmpty(sku.getCategoryId())){
                    criteria.andEqualTo("categoryId",sku.getCategoryId());
            }
            // 类目名称
            if(!StringUtils.isEmpty(sku.getCategoryName())){
                    criteria.andEqualTo("categoryName",sku.getCategoryName());
            }
            // 品牌名称
            if(!StringUtils.isEmpty(sku.getBrandName())){
                    criteria.andEqualTo("brandName",sku.getBrandName());
            }
            // 规格
            if(!StringUtils.isEmpty(sku.getSpec())){
                    criteria.andEqualTo("spec",sku.getSpec());
            }
            // 销量
            if(!StringUtils.isEmpty(sku.getSaleNum())){
                    criteria.andEqualTo("saleNum",sku.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(sku.getCommentNum())){
                    criteria.andEqualTo("commentNum",sku.getCommentNum());
            }
            // 商品状态 1-正常，2-下架，3-删除
            if(!StringUtils.isEmpty(sku.getStatus())){
                    criteria.andEqualTo("status",sku.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        skuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Sku
     * @param sku
     */
    @Override
    public void update(Sku sku){
        skuMapper.updateByPrimaryKey(sku);
    }

    /**
     * 增加Sku
     * @param sku
     */
    @Override
    public void add(Sku sku){
        skuMapper.insert(sku);
    }

    /**
     * 根据ID查询Sku
     * @param id
     * @return
     */
    @Override
    public Sku findById(Integer id){
        return  skuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Sku全部数据
     * @return
     */
    @Override
    public List<Sku> findAll() {
        return skuMapper.selectAll();
    }

    /**
     * 创建商品
     *
     * @param goods
     */
    @Override
    public void saveGoods(Goods goods) {
        Spu spu = goods.getSpu();

        if(spu.getId() == null){
            spu.setId( String.valueOf( idWorker.nextId() ) );
            int insertSpu = spuMapper.insertSelective( spu );
            log.info( "添加spu成功条数：{}",insertSpu );
        }else{
            spuMapper.updateByPrimaryKey( spu );

            //删除之前的List
            Sku sku = new Sku();
            sku.setSpuId( spu.getId() );
            skuMapper.delete( sku );
        }

        Category category = categoryMapper.selectByPrimaryKey( spu.getCategory3Id() );
        Brand brand = brandMapper.selectByPrimaryKey( spu.getBrandId() );

        List<Sku> skus = goods.getSkuList();
        for (Sku sku : skus) {
            sku.setId( String.valueOf( idWorker.nextId() ) );
            //商品名字：spu.name +规格
            String name = spu.getName();
            //防止空指针
            if (StringUtils.isEmpty( sku.getSpec() )){
                sku.setSpec( "{}" );
            }
            Map<String, String> specMap = JSON.parseObject( sku.getSpec(), Map.class );
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                name += " " + entry.getValue();
            }
            sku.setName( name );
            sku.setCreateTime( new Date() );
            sku.setUpdateTime( new Date() );
            sku.setSpuId( spu.getId() );
            //分类id--->3级分类id & name
            sku.setCategoryId( spu.getCategory3Id() );
            sku.setCategoryName( category.getName() );
            sku.setBrandName( brand.getName() );
            int i = skuMapper.insertSelective( sku );
            log.info("添加sku成功条数：{}",i);
        }
    }

    /**
     * 根据spuId查询商品信息
     *
     * @param spuId
     * @return
     */
    @Override
    public Goods findGoodsBySpuId(String spuId) {
        Spu spu = spuMapper.selectByPrimaryKey( spuId );
        if (Objects.isNull(spu)){
            return null;
        }
        Sku sku = new Sku();
        sku.setSpuId( spuId );
        List<Sku> skuList = skuMapper.select( sku );
        Goods goods = new Goods();
        goods.setSpu( spu );
        goods.setSkuList( skuList );
        return goods;
    }
}
