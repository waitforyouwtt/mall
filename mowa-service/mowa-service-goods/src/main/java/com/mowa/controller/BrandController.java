package com.mowa.controller;

import com.mowa.Result;
import com.mowa.goods.pojo.Brand;
import com.mowa.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 0:42
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
@CrossOrigin
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping("/save")
    public Result save(@RequestBody Brand brand){
        return Result.success( brandService.save(brand) );
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id,@RequestBody Brand brand){
        brand.setId( id );
        return Result.success( brandService.update(brand) );
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        return Result.success( brandService.delete(id) );
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") int id){
        return Result.success( brandService.findById( id ) );
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return Result.success(brandService.findAll());
    }

    @GetMapping("/search")
    public Result search(Brand brand){
        return Result.success( brandService.findList(brand) );
    }
    
    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        return Result.success( brandService.findPage(pageNum,pageSize) );
    }

    @GetMapping("/findListPage/{pageNum}/{pageSize}")
    public Result findListPage(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize, Brand brand){
        return Result.success( brandService.findPage(pageNum,pageSize, brand) );
    }

    @GetMapping("/category/{categoryId}")
    public Result findByCategoryId(@PathVariable ("categoryId") int categoryId){
        return Result.success( brandService.findByCategoryId(categoryId) );
    }

}
