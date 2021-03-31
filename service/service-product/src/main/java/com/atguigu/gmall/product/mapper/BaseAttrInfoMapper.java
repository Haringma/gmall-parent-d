package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {
    List<BaseAttrInfo> getAttrInfoList(@Param("category1Id")Long category1Id,
                                       @Param("category2Id")Long category2Id,
                                       @Param("category3Id")Long category3Id);

    Map<String,Object> getCategoryView(Long category3Id);

    List<SpuSaleAttrValue> getSpuSaleAttrListCheckBySku(@Param("skuId")Long skuId,
                                                        @Param("spuId")Long spuId);
}
