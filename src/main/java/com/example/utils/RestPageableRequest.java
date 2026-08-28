package com.example.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPageableRequest {

	private int pageNumber;
	private int PageSize;
	private String columnName;
	private boolean asc;
}
