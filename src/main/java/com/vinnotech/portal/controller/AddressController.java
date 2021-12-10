package com.vinnotech.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PutMapping("/permanent/{empId}")
	public void addPAddress(@RequestBody Address address, @PathVariable Long empId) {
		addressService.addPAddress(address, empId);
	}
	
	@PutMapping("/current/{empId}")
	public void addCAddress(@RequestBody Address address, @PathVariable Long empId) {
		addressService.addCAddress(address, empId);
	}
}
