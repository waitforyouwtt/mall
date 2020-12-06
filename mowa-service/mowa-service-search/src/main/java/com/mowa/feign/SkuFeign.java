package com.mowa.feign;

import com.mowa.Result;
import com.mowa.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @GetMapping("/findAll")
    Result<List<Sku>> findAll();
}
