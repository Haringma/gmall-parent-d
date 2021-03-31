package com.atguigu.gmall.product.api;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.service.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductApiController {

    @Autowired
    private BaseManageService baseManageService;

    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable(name = "skuId") Long skuId) {
        return baseManageService.getSkuInfo(skuId);
    }

    @GetMapping("inner/getCategoryView/{category3Id}")
    public Map<String, Object> getCategoryView(@PathVariable(name = "category3Id") Long category3Id) {
        return baseManageService.getCategoryView(category3Id);
    }

    @GetMapping("inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable(name = "skuId") Long skuId) {
        return baseManageService.getSkuPrice(skuId);
    }

    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttrValue> getSpuSaleAttrListCheckBySku(@PathVariable(name = "skuId") Long skuId,
                                                               @PathVariable(name = "spuId") Long spuId) {
        return baseManageService.getSpuSaleAttrListCheckBySku(skuId, spuId);
    }

}
