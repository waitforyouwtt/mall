package com.mowa.controller;

import com.mowa.Result;
import com.mowa.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @ApiOperation(value = "数据库数据同步es缓存",notes = "数据库数据同步es缓存",tags = {"SearchController"})
    @GetMapping("/syncSkuEs")
    public Result syncSkuList(){
        searchService.importSkuList();
        return Result.success();
    }

    @ApiOperation(value = "根据id查询文档数据",notes = "根据id查询文档数据",tags = {"SearchController"})
    @GetMapping("/queryDocumentById")
    public Result queryDocumentById(@RequestParam("id") String id){
        return Result.success(searchService.queryDocumentById( id ));
    }

    @ApiOperation(value = "根据关键词查询es",notes = "根据关键词查询es",tags = {"SearchController"})
    @GetMapping("/search")
    public Result search(@RequestParam("map") Map<String,String> map){
        return Result.success( searchService.search( map ) );
    }

}

