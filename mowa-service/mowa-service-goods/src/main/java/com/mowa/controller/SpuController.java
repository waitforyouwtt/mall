package com.mowa.controller;

import com.github.pagehelper.PageInfo;
import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.goods.pojo.Spu;
import com.mowa.service.SpuService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Api(value = "SpuController")
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Spu条件分页查询",notes = "分页条件查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu, @PathVariable int page, @PathVariable int size){
        //调用SpuService实现分页条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Spu分页查询",notes = "分页查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @ApiOperation(value = "Spu条件查询",notes = "条件查询Spu方法详情",tags = {"SpuController"})
    @PostMapping(value = "/search" )
    public Result<List<Spu>> findList(@RequestBody(required = false) @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu){
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return new Result<List<Spu>>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID删除",notes = "根据ID删除Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用SpuService实现根据主键删除
        spuService.delete(id);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID修改",notes = "根据ID修改Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu, @PathVariable String id){
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        spuService.update(spu);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"修改成功");
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @ApiOperation(value = "Spu添加",notes = "添加Spu方法详情",tags = {"SpuController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Spu对象",value = "传入JSON数据",required = true) Spu spu){
        //调用SpuService实现添加Spu
        spuService.add(spu);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"添加成功");
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID查询",notes = "根据ID查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable Integer id){
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        return new Result<Spu>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Spu",notes = "查询所Spu有方法详情",tags = {"SpuController"})
    @GetMapping
    public Result<List<Spu>> findAll(){
        //调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return new Result<List<Spu>>(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",list) ;
    }

    @ApiOperation(value = "审核上架",notes = "审核上架",tags = {"SpuController"})
    @PutMapping("/audit/{spuId}")
    public Result audit (@PathVariable("spuId") String spuId){
        try{
            spuService.audit( spuId );
        }catch (Exception e){
            return Result.error(  );
        }
        return Result.success( );
    }

    @ApiOperation(value = "上架",notes = "上架",tags = {"SpuController"})
    @PutMapping("/put/{spuId}")
    public Result put (@PathVariable("spuId") String spuId){
        try{
            spuService.put( spuId );
        }catch (Exception e){
            return Result.error(  );
        }
        return Result.success( );
    }

    @ApiOperation(value = "下架",notes = "下架",tags = {"SpuController"})
    @PutMapping("/pull/{spuId}")
    public Result pull (@PathVariable("spuId") String spuId){
        try{
            spuService.pull( spuId );
        }catch (Exception e){
            return Result.error(  );
        }
        return Result.success( );
    }

    @ApiOperation(value = "批量上架",notes = "批量上架",tags = {"SpuController"})
    @PutMapping("/putMany")
    public Result put (@RequestBody String[] spuIds){
        try{
            spuService.putMany( spuIds );
        }catch (Exception e){
            return Result.error(  );
        }
        return Result.success( );
    }
}
