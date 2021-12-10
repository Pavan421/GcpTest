package com.vinnotech.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.service.RequestQuotService;

@RestController
@RequestMapping("/api/reqquot")
public class RequestQuotController {
	@Autowired
	private RequestQuotService requestQuotService;
	
	@PostMapping
	public RequestQuot createRequestQuot(@RequestBody RequestQuot requestQuot) {
		return requestQuotService.createRequestQuot(requestQuot);
	}
	public List<RequestQuot> getAllRequestQuots(){
		return requestQuotService.getAllRequestQuots();
	}
	
}
