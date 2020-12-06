package com.mowa.controller;

import com.mowa.Result;
import com.mowa.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/syncSkuEs")
    public Result syncSkuList(){
        searchService.importSkuList();
        return Result.success();
    }

}

