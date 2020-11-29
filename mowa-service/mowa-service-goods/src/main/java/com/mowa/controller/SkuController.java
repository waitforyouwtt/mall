package com.mowa.controller;

import com.github.pagehelper.PageInfo;
import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.goods.pojo.Goods;
import com.mowa.goods.pojo.Sku;
import com.mowa.service.SkuService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Api(value = "SkuController")
@RestController
@RequestMapping("/sku")
@CrossOrigin
@Slf4j
public class SkuController {

    @Autowired
    private SkuService skuService;

    /***
     * Sku分页条件搜索实现
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Sku条件分页查询",notes = "分页条件查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku, @PathVariable int page, @PathVariable int size){
        //调用SkuService实现分页条件查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(sku, page, size);
        return new Result(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * Sku分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Sku分页查询",notes = "分页查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用SkuService实现分页查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param sku
     * @return
     */
    @ApiOperation(value = "Sku条件查询",notes = "条件查询Sku方法详情",tags = {"SkuController"})
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku){
        //调用SkuService实现条件查询Sku
        List<Sku> list = skuService.findList(sku);
        return new Result<List<Sku>>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID删除",notes = "根据ID删除Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用SkuService实现根据主键删除
        skuService.delete(id);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }

    /***
     * 修改Sku数据
     * @param sku
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID修改",notes = "根据ID修改Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku, @PathVariable Integer id){
        //设置主键值
        sku.setId(id.toString());
        //调用SkuService实现修改Sku
        skuService.update(sku);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"修改成功");
    }

    /***
     * 新增Sku数据
     * @param sku
     * @return
     */
    @ApiOperation(value = "Sku添加",notes = "添加Sku方法详情",tags = {"SkuController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Sku对象",value = "传入JSON数据",required = true) Sku sku){
        //调用SkuService实现添加Sku
        skuService.add(sku);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"添加成功");
    }

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID查询",notes = "根据ID查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable Integer id){
        //调用SkuService实现根据主键查询Sku
        Sku sku = skuService.findById(id);
        return new Result<Sku>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",sku);
    }

    /***
     * 查询Sku全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Sku",notes = "查询所Sku有方法详情",tags = {"SkuController"})
    @GetMapping
    public Result<List<Sku>> findAll(){
        //调用SkuService实现查询所有Sku
        List<Sku> list = skuService.findAll();
        return new Result<List<Sku>>(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",list) ;
    }

    @ApiOperation(value = "创建商品信息",notes = "创建商品信息",tags = {"SkuController"})
    @PostMapping("/saveGoods")
    public Result saveGoods(@RequestBody Goods goods){
        try{
            skuService.saveGoods(goods);
        }catch (Exception e){
            log.info("创建商品失败:{}",e.getMessage());
            return Result.error(  );
        }
        return Result.success(  );
    }

    @ApiOperation(value = "根据spuId查询商品信息",notes = "查询商品信息",tags = {"SkuController"})
    @GetMapping("/goods/{spuId}")
    public Result findGoodsBySpuId(@PathVariable("spuId") String spuId){
        return Result.success( skuService.findGoodsBySpuId(spuId) );
    }
}
