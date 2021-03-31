package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface BaseManageService {

    List<BaseCategory1> getCategory1();

    List<BaseCategory2> getCategory2(Long category1Id);

    List<BaseCategory3> getCategory3(Long category2Id);

    List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);

    IPage<SpuInfo> getSpuInfo(long page, long limit, long category3Id);

    List<BaseSaleAttr> getBaseSaleAttr();

    List<BaseTrademark> getTrademarkList();

    void saveSpuInfo(SpuInfo spuInfo);
}
