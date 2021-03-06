package com.mowa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mowa.dao.SpuMapper;
import com.mowa.goods.pojo.Spu;
import com.mowa.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;


    /**
     * Spu条件+分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu){
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     * @param spu
     * @return
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 
            if(!StringUtils.isEmpty(spu.getId())){
                    criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                    criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名
            if(!StringUtils.isEmpty(spu.getName())){
                    criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题
            if(!StringUtils.isEmpty(spu.getCaption())){
                    criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(spu.getBrandId())){
                    criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类
            if(!StringUtils.isEmpty(spu.getCategory1Id())){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(spu.getCategory2Id())){
                    criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(spu.getCategory3Id())){
                    criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spu.getTemplateId())){
                    criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id
            if(!StringUtils.isEmpty(spu.getFreightId())){
                    criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片
            if(!StringUtils.isEmpty(spu.getImage())){
                    criteria.andEqualTo("image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                    criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务
            if(!StringUtils.isEmpty(spu.getSaleService())){
                    criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                    criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                    criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                    criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量
            if(!StringUtils.isEmpty(spu.getSaleNum())){
                    criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(spu.getCommentNum())){
                    criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否上架
            if(!StringUtils.isEmpty(spu.getIsMarketAble())){
                    criteria.andEqualTo("isMarketAble",spu.getIsMarketAble());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                    criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否删除
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                    criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态
            if(!StringUtils.isEmpty(spu.getStatus())){
                    criteria.andEqualTo("status",spu.getStatus());
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
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Spu
     * @param spu
     */
    @Override
    public void update(Spu spu){
        spuMapper.updateByPrimaryKey(spu);
    }

    /**
     * 增加Spu
     * @param spu
     */
    @Override
    public void add(Spu spu){
        spuMapper.insert(spu);
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu findById(Integer id){
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 审核商品
     *
     * @param spuId
     */
    @Override
    public void audit(String spuId) {
        Spu spu = spuMapper.selectByPrimaryKey( spuId );
        if (spu.getIsDelete().equalsIgnoreCase( "1" )){
            throw new RuntimeException( "不能对已删除的商品进行审核" );
        }
        //审核通过 & 上架
        spu.setStatus( "1" );
        spu.setIsMarketAble( "1" );
        spuMapper.updateByPrimaryKeySelective( spu );
    }

    /**
     * 上架商品
     *
     * @param spuId
     */
    @Override
    public void put(String spuId) {
        Spu spu = spuMapper.selectByPrimaryKey( spuId );
        if (spu.getIsDelete().equalsIgnoreCase( "1" )){
            throw new RuntimeException( "不能对已删除的商品进行上架" );
        }
        if (!spu.getStatus().equalsIgnoreCase( "1" )){
            throw new RuntimeException( "未通过审核的商品不能进行上架" );
        }
        //上架
        spu.setIsMarketAble( "1" );
        spuMapper.updateByPrimaryKeySelective( spu );
    }

    /**
     * 下架商品
     *
     * @param spuId
     */
    @Override
    public void pull(String spuId) {
        Spu spu = spuMapper.selectByPrimaryKey( spuId );
        if (spu.getIsDelete().equalsIgnoreCase( "1" )){
            throw new RuntimeException( "不能对已删除的商品进行下架" );
        }
        //下架
        spu.setIsMarketAble( "0" );
        spuMapper.updateByPrimaryKeySelective( spu );
    }

    /**
     * 上架商品
     *
     * @param spuIds
     */
    @Override
    public void putMany(String[] spuIds) {
        Example example = new Example( Spu.class );
        Example.Criteria criteria = example.createCriteria();

        criteria.andIn( "id", Arrays.asList(spuIds) );
        //未删除
        criteria.andEqualTo( "isDelete","0" );
        //已审核
        criteria.andEqualTo( "status","1" );

        Spu spu = new Spu();
        //上架
        spu.setIsMarketAble( "1" );
        spuMapper.updateByExampleSelective( spu,example );
    }
}
