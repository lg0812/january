package com.Jan.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.Jan.model.User;

public interface UserMapper {
	// @Select("SELECT * FROM user WHERE id = #{id}")
	User getUser(long userId);

	List<User> getUsers(@Param("name") String name);
}
