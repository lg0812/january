package com.Jan.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Jan.model.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User getUser(@Param("id") long id);
}
