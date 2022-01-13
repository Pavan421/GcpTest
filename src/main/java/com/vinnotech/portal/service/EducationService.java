package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Education;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class EducationService {

	private static final String CLASSNAME = "EducationService";
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public String updateEducationDetails(Education educationDetails, Long empId) {
		String methodName = "updateEducationDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			emp.setEducation(educationDetails);
			employeeRepository.save(emp);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "EducationDetails Updated Successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while updating EducationDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

	public Education getEducationDetails(Long empId) {
		String methodName = "getEducationDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			Education empEducationDetails = emp.getEducation();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return empEducationDetails;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting EducationDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}
}
