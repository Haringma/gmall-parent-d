package com.atguigu.gmall.feignClient;

import com.atguigu.gmall.fallback.ProductDegradeFeignClient;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@FeignClient(value = "service-product",fallback = ProductDegradeFeignClient.class)
public interface ProductFeignClient {

    @GetMapping("api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable(name = "skuId") Long skuId);

    @GetMapping("api/product/inner/getCategoryView/{category3Id}")
    public Map<String,Object> getCategoryView(@PathVariable(name = "category3Id") Long category3Id);

    @GetMapping("api/product/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable(name = "skuId") Long skuId);

    @GetMapping("api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttrValue> getSpuSaleAttrListCheckBySku(@PathVariable(name = "skuId") Long skuId,
                                                               @PathVariable(name = "spuId") Long spuId);

}
