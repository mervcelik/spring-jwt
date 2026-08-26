package com.example.service;

import com.example.jwt.AuthResponse;
import com.example.jwt.RefreshTokenRequest;
import com.example.model.RefreshToken;
import com.example.model.User;

public interface IRefreshTokenService {

	AuthResponse refreshToken(RefreshTokenRequest request);

	RefreshToken createRefreshToken(User user);

}
