package com.Jan.mappers;

import org.apache.ibatis.annotations.Param;

import com.Jan.may.GoodsInfo;

public interface GoodsInfoMapper {
	GoodsInfo getGoodsInfoById(@Param("id") Long id);
}
