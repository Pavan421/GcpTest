package com.vinnotech.portal.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.config.CustomUserDetailsService;
import com.vinnotech.portal.config.JwtUtil;
import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.AuthenticationRequest;
import com.vinnotech.portal.model.AuthenticationResponse;
import com.vinnotech.portal.model.UserReg;
import com.vinnotech.portal.repository.UserRepository;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	/*
	 * @RequestMapping("/") public ModelAndView homePage() { return new
	 * ModelAndView("/index.html"); }
	 */

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), "User disabled");
		} catch (BadCredentialsException e) {
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), "Please enter valid credentials");
		} catch (Exception e) {
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), "Please enter valid credentials");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserReg user) throws Exception {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return ResponseEntity.ok(userRepository.save(user));
	}

	@GetMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
		Map<String, Object> expectedMap = getMapFromJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	public Map<String, Object> getMapFromJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}
