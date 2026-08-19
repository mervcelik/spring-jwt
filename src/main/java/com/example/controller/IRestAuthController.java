package com.example.controller;

import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;

public interface IRestAuthController {
	public DtoUser register(AuthRequest request);
}
