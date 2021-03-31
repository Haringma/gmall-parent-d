package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.feignClient.ProductFeignClient;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Map<String, Object> getBySkuId(Long skuId) {
        Map<String, Object> result = new HashMap<>();

        //1.获取sku基本信息与图片信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        result.put("skuInfo", skuInfo);

        //2.获取分类信息
        Map<String,Object> categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
        result.put("categoryView", categoryView);

        //3.获取价格信息
        BigDecimal skuPrice = productFeignClient.getSkuPrice(skuId);
        result.put("price",skuPrice);

        //4.获取销售信息
        List<SpuSaleAttrValue> spuSaleAttrListCheckBySku = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());
        result.put("spuSaleAttrList",spuSaleAttrListCheckBySku);

        return result;
    }
}
