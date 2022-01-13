package com.vinnotech.portal.controller;

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

import com.vinnotech.portal.model.Education;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.EducationService;

@RestController
@RequestMapping("/api/education")
public class EducationController {
	private static final String CLASSNAME = "EducationController";
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationController.class);

	@Autowired
	private EducationService educationService;

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/update/{empId}")
	public ResponseEntity<String> updateEducationDetails(@RequestBody Education education,
			@PathVariable("empId") Long empId) {
		String methodName = "updateEducationDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String updatedEducationDetails = educationService.updateEducationDetails(education, empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "updating Educationdetails");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedEducationDetails);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{empId}")
	public ResponseEntity<Education> getEducationDetails(@PathVariable("empId") Long empId) {
		String methodName = "getEducationDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Education empEducationDetails = educationService.getEducationDetails(empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting Educationdetails");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empEducationDetails);
	}
}
