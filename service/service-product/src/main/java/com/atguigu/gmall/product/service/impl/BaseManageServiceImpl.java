package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.BaseManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseManageServiceImpl implements BaseManageService {

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;

    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;

    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public List<BaseCategory1> getCategory1() {
        return baseCategory1Mapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        return baseCategory2Mapper.selectList(new QueryWrapper<BaseCategory2>().eq("category1_id", category1Id));
    }

    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>().eq("category2_id", category2Id));
    }

    @Override
    public List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.getAttrInfoList(category1Id, category2Id, category3Id);
    }

    @Override
    public IPage<SpuInfo> getSpuInfo(long page, long limit, long category3Id) {
        IPage<SpuInfo> iPage = new Page<>(page, limit);
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();

        wrapper.eq("category3_id", category3Id);
        return spuInfoMapper.selectPage(iPage, wrapper);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttr() {
        return baseSaleAttrMapper.selectList(new QueryWrapper<BaseSaleAttr>().orderByAsc("id"));
    }

    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Override
    public List<BaseTrademark> getTrademarkList() {
        return baseTrademarkMapper.selectList(null);
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //1.保存品牌信息
        int insert = spuInfoMapper.insert(spuInfo);
        if (insert != 0) {
            //2.保存品牌图片
            Long spuId = spuInfo.getId();
            List<SpuImage> spuImageList = spuInfo.getSpuImageList();
            spuImageList.forEach(spuImage -> {
                spuImage.setSpuId(spuId);
                spuImageMapper.insert(spuImage);
            });
            //3.保存销售属性
            List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
            spuSaleAttrList.forEach(spuSaleAttr -> {
                spuSaleAttr.setSpuId(spuId);
                int insert1 = spuSaleAttrMapper.insert(spuSaleAttr);
                if (insert1 != 0) {
                    //4.保存销售属性值表
                    List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                    spuSaleAttrValueList.forEach(spuSaleAttrValue -> {
                        spuSaleAttrValue.setSpuId(spuId);
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    });
                }
            });
        }
    }

    @Override
    public List<SpuImage> getSpuImageList(Long spuId) {
        return spuImageMapper.selectList(new QueryWrapper<SpuImage>().eq("spu_id",spuId));
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.getSpuSaleAttrList(spuId);
    }

    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        int insert = skuInfoMapper.insert(skuInfo);
        if (insert != 0){
            Long skuId = skuInfo.getId();
            List<SkuImage> skuImageList = skuInfo.getSkuImageList();
            skuImageList.forEach(skuImage -> {
                skuImage.setSkuId(skuId);
                skuImageMapper.insert(skuImage);
            });
            List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
            skuAttrValueList.forEach(skuAttrValue -> {
                skuAttrValue.setSkuId(skuId);
                skuAttrValueMapper.insert(skuAttrValue);
            });
            List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            skuSaleAttrValueList.forEach(skuSaleAttrValue -> {
                skuSaleAttrValue.setSkuId(skuId);
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            });
        }
    }

    @Override
    public IPage<SkuInfo> getSkuInfoList(Long page, Long limit) {
        IPage<SkuInfo> skuInfoPage = new Page<>(page, limit);

        return skuInfoMapper.selectPage(skuInfoPage, null);
    }

    @Override
    public void onSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public void cancelSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public IPage<BaseTrademark> getBaseTrademark(Long page, Long limit) {
        IPage<BaseTrademark> baseTrademarkIPage = new Page<>(page, limit);

        return baseTrademarkMapper.selectPage(baseTrademarkIPage, null);
    }
}
