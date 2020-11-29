package com.mowa.controller;

import com.github.pagehelper.PageInfo;
import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.goods.pojo.Para;
import com.mowa.service.ParaService;
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
@Api(value = "ParaController")
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    @Autowired
    private ParaService paraService;

    /***
     * Para分页条件搜索实现
     * @param para
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Para条件分页查询",notes = "分页条件查询Para方法详情",tags = {"ParaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Para对象",value = "传入JSON数据",required = false) Para para, @PathVariable int page, @PathVariable int size){
        //调用ParaService实现分页条件查询Para
        PageInfo<Para> pageInfo = paraService.findPage(para, page, size);
        return new Result(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * Para分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Para分页查询",notes = "分页查询Para方法详情",tags = {"ParaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用ParaService实现分页查询Para
        PageInfo<Para> pageInfo = paraService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param para
     * @return
     */
    @ApiOperation(value = "Para条件查询",notes = "条件查询Para方法详情",tags = {"ParaController"})
    @PostMapping(value = "/search" )
    public Result<List<Para>> findList(@RequestBody(required = false) @ApiParam(name = "Para对象",value = "传入JSON数据",required = false) Para para){
        //调用ParaService实现条件查询Para
        List<Para> list = paraService.findList(para);
        return new Result<List<Para>>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Para根据ID删除",notes = "根据ID删除Para方法详情",tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用ParaService实现根据主键删除
        paraService.delete(id);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }

    /***
     * 修改Para数据
     * @param para
     * @param id
     * @return
     */
    @ApiOperation(value = "Para根据ID修改",notes = "根据ID修改Para方法详情",tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Para对象",value = "传入JSON数据",required = false) Para para, @PathVariable Integer id){
        //设置主键值
        para.setId(id);
        //调用ParaService实现修改Para
        paraService.update(para);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"修改成功");
    }

    /***
     * 新增Para数据
     * @param para
     * @return
     */
    @ApiOperation(value = "Para添加",notes = "添加Para方法详情",tags = {"ParaController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Para对象",value = "传入JSON数据",required = true) Para para){
        //调用ParaService实现添加Para
        paraService.add(para);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"添加成功");
    }

    /***
     * 根据ID查询Para数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Para根据ID查询",notes = "根据ID查询Para方法详情",tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable Integer id){
        //调用ParaService实现根据主键查询Para
        Para para = paraService.findById(id);
        return new Result<Para>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",para);
    }

    /***
     * 查询Para全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Para",notes = "查询所Para有方法详情",tags = {"ParaController"})
    @GetMapping
    public Result<List<Para>> findAll(){
        //调用ParaService实现查询所有Para
        List<Para> list = paraService.findAll();
        return new Result<List<Para>>(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",list) ;
    }

    @ApiOperation(value = "根据分类id查询Para",notes = "根据分类id查询Para",tags = {"ParaController"})
    @GetMapping("/category/{categoryId}")
    public Result findCategoryId(@PathVariable("categoryId") String categoryId){
        return Result.success( paraService.findCategoryId(categoryId) );
    }
}
