package com.vinnotech.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.AddressRepository;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public void addPAddress(Address address, Long empId) {
		Employee emp = employeeRepository.findById(empId).get();
		emp.setPermanentAddress(address);
		address.setEmpPAddress(emp);
		employeeRepository.save(emp);
	}
	public void addCAddress(Address address, Long empId) {
		Employee emp = employeeRepository.findById(empId).get();
		emp.setCurrentAddress(address);
		address.setEmpCAddress(emp);
		employeeRepository.save(emp);
	}
}
