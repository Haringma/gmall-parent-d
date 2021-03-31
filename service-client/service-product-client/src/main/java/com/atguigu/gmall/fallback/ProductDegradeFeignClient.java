package com.atguigu.gmall.fallback;

import com.atguigu.gmall.feignClient.ProductFeignClient;
import com.atguigu.gmall.model.product.SkuInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDegradeFeignClient implements ProductFeignClient {
    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        return null;
    }
}
