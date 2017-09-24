package com.Jan.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.Jan.model.User;

@MapperScan
public interface UserMapper {
	@Select("SELECT * FROM user WHERE id = #{id}")
	User getUser(@Param("id") long userId);
}
