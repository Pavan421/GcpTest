package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.EmergencyContact;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class EmergencyContactService {

	private static final String CLASSNAME = "EmergencyContactService";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmergencyContactService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public String updateEmergencyContact(EmergencyContact emergencyContact, Long empId) {
		String methodName = "updateEmergencyContact";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			emp.setEmergencyContact(emergencyContact);
			employeeRepository.save(emp);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "EmergencyContact Details Updated Successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while updating EmergencyContact Details" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

	public EmergencyContact getEmergencyContactDetails(Long empId) {
		String methodName = "getEmergencyContactDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			EmergencyContact empEmergencyContactDetails = emp.getEmergencyContact();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return empEmergencyContactDetails;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting EmergencyContact Details" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		}
	}
}
