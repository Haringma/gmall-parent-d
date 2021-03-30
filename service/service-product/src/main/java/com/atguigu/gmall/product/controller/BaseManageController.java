package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品基础属性接口")
@RestController
@RequestMapping("admin/product")
public class BaseManageController {

    @Autowired
    private BaseManageService baseManageService;

    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> list = baseManageService.getCategory1();
        return Result.ok(list);
    }

    @GetMapping("/getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> list = baseManageService.getCategory2(category1Id);
        return Result.ok(list);
    }

    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> list = baseManageService.getCategory3(category2Id);
        return Result.ok(list);
    }

    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable Long category1Id,
                                                   @PathVariable Long category2Id,
                                                   @PathVariable Long category3Id) {
        List<BaseAttrInfo> list = baseManageService.attrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(list);
    }
}
