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

import com.vinnotech.portal.model.EmergencyContact;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.EmergencyContactService;

@RestController
@RequestMapping("/api/emergencycontact")
public class EmergencyContactController {
	
	private static final String CLASSNAME = "EmergencyContactController";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmergencyContactController.class);

	@Autowired(required=true)
	private EmergencyContactService emergencyContactService;

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/update/{empId}")
	public ResponseEntity<String> updateEmergencyContactDetails(@RequestBody EmergencyContact emergencyContact,
			@PathVariable("empId") Long empId) {
		String methodName = "updateEmergencyContactDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String updatedEmergencyContact = emergencyContactService.updateEmergencyContact(emergencyContact, empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "updating emergencyContact details");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedEmergencyContact);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{empId}")
	public ResponseEntity<EmergencyContact> getEmergencyContactDetails(@PathVariable("empId") Long empId) {
		String methodName = "getEmergencyContactDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		EmergencyContact empEmergencyContactDetails = emergencyContactService.getEmergencyContactDetails(empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting emergencyContact details");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empEmergencyContactDetails);
	}
}