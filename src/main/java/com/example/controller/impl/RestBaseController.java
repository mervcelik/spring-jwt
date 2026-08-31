package com.example.controller.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.utils.PagerUtil;
import com.example.utils.RestPageableEntity;
import com.example.utils.RestPageableRequest;
import com.example.utils.RestRootEntity;

public class RestBaseController {

	public Pageable toPageable(RestPageableRequest pageableRequest) {
		return PagerUtil.toPageable(pageableRequest);
	}
	
	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> content) {
		return PagerUtil.toPageableResponse(page, content);
	}
	
	public <T> RestRootEntity<T> ok(T payload) {
		
		return RestRootEntity.ok(payload);
	}
}
