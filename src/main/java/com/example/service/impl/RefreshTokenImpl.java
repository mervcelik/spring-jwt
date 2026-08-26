package com.example.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.AuthResponse;
import com.example.jwt.JwtService;
import com.example.jwt.RefreshTokenRequest;
import com.example.model.RefreshToken;
import com.example.model.User;
import com.example.repository.RefreshTokenRepository;
import com.example.service.IRefreshTokenService;

@Service
public class RefreshTokenImpl implements IRefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());

		if (optional.isEmpty()) {
			System.out.println("Refresh token geçersizdir: " + request.getRefreshToken());
		}
		
		RefreshToken refreshToken=optional.get();
		if(!isRefreshTokenExpired(refreshToken.getExpireDate())) {
			System.out.println("Refresh Token expire olmuştur: "+request.getRefreshToken());
		}
		
		String acessToken = jwtService.generateToken(refreshToken.getUser());
		RefreshToken savedRefreshToken=createRefreshToken(refreshToken.getUser());
		return new AuthResponse(acessToken,savedRefreshToken.getRefreshToken());
	}

	public boolean isRefreshTokenExpired(Date expiredDate) {
		return new Date().before(expiredDate);

	}
	
	@Override
	public RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken= new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setUser(user);
		
		refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

}
