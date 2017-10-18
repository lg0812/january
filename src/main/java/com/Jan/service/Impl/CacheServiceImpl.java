package com.Jan.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.service.CacheService;

@Service
@Transactional
public class CacheServiceImpl implements CacheService {

	@Override
	public String testStr() {
		// TODO Auto-generated method stub
		return "success";
	}

}
