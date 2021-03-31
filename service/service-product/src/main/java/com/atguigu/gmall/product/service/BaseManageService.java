package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BaseManageService {

    List<BaseCategory1> getCategory1();

    List<BaseCategory2> getCategory2(Long category1Id);

    List<BaseCategory3> getCategory3(Long category2Id);

    List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);

    IPage<SpuInfo> getSpuInfo(long page, long limit, long category3Id);

    List<BaseSaleAttr> getBaseSaleAttr();

    List<BaseTrademark> getTrademarkList();

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuImage> getSpuImageList(Long spuId);

    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    void saveSkuInfo(SkuInfo skuInfo);

    IPage<SkuInfo> getSkuInfoList(Long page, Long limit);

    void onSale(Long skuId);

    void cancelSale(Long skuId);

    IPage<BaseTrademark> getBaseTrademark(Long page, Long limit);

    SkuInfo getSkuInfo(Long skuId);

    Map<String,Object> getCategoryView(Long category3Id);

    BigDecimal getSkuPrice(Long skuId);

    List<SpuSaleAttrValue> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);
}
