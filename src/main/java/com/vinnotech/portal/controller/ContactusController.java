package com.vinnotech.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.ContactUS;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.ContactusService;

@RestController
@RequestMapping("api/contactus")
public class ContactusController {

	private static final String CLASSNAME = "ContactusController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactusController.class);

	@Autowired
	private ContactusService contactusService;

	@PostMapping("/create")
	public ResponseEntity<ContactUS> saveContactUS(@RequestBody ContactUS contactus) {
		String methodName = "saveContactUS";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		ContactUS contactUs = contactusService.addContactus(contactus);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating contactus");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(contactUs);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_ONLY)
	@GetMapping("{offset}/{pageSize}")
	public ResponseEntity<Page<ContactUS>> contactus(@PathVariable int offset, @PathVariable int pageSize) {
		String methodName = "contactus";
		LOGGER.info(CLASSNAME + ":Entering into the" + methodName + " method");
		Page<ContactUS> listContactUs = contactusService.getAllContactus(offset, pageSize);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting contactusList");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(listContactUs);
	}
}
