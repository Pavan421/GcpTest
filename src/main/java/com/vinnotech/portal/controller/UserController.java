package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.ChangePwd;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.UserReg;
import com.vinnotech.portal.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final String CLASSNAME = "UserController";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Getting all users
	 * 
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_ONLY)
	@GetMapping
	public ResponseEntity<List<UserReg>> getAllUsers() {
		String methodName = "getAllUsers";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		List<UserReg> users = userService.getAllUsers();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Resetting password");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(users);
	}

	/**
	 * Reset password for Existing user
	 * 
	 * @param username
	 * @return
	 */
	@PutMapping("/resetpassword/{username}")
	public ResponseEntity<String> forgotPassword(@PathVariable("username") String username) {
		String methodName = "forgotPassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		String successResponse = userService.forgotPassword(username);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Resetting password");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(successResponse);
	}

	/**
	 * Change password for Logged in user
	 * 
	 * @param changePwd
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY)
	@PutMapping("/changepassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePwd changePwd) {
		String methodName = "changePassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		String successResponse = userService.changePassword(changePwd);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Changing password");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(successResponse);
	}
}
