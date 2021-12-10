package com.vinnotech.portal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public Employee getEmployee(Long empId) {
		Optional<Employee> employee = empRepository.findById(empId);
		if (employee.isPresent() && !employee.get().isEmployeeDeleted()) {
			return employee.get();
		}
		throw new ValidateException("No records found");
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = empRepository.findAll();
		return employees.stream().filter(e -> e.isEmployeeDeleted() != true).collect(Collectors.toList());
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		try {
			return empRepository.save(emp);
			// System.out.println("Employee has save successfully");
		} catch (Exception ex) {
			System.out.println("Failed to create employee.." + ex.getMessage());
			ex.printStackTrace();
			throw new ValidateException("Failed to create employee..");
		}
	}

	@Override
	public void deleteEmployee(Employee emp) {
		emp.setEmployeeDeleted(Boolean.TRUE);
		empRepository.save(emp);
	}

	/*
	 * @Override public List<Employee> getAllEmployeeswitSorting(String field){
	 * return empRepository.findAll(Sort.by(Sort.Direction.ASC,field));
	 * 
	 * }
	 * 
	 * @Override public List<Employee> getAllEmployeeswitSortingDesc(String field){
	 * return empRepository.findAll(Sort.by(Sort.Direction.DESC,field));
	 * 
	 * }
	 */
	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiDesc(int offset, int pageSize, String field) {
		Page<Employee> employees = empRepository
				.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		return employees;
	}

	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiASC(int offset, int pageSize, String field) {
		Page<Employee> employees = empRepository
				.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return employees;
	}

	public Page<Employee> getAllEmployeeswithSortAndPagiDelDesc(boolean isEmpDeleted, int offset, int pageSize,
			String field) {
		Page<Employee> employees = empRepository.findByEmpWithDeletedStatus(isEmpDeleted,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		return employees;
	}

	public Page<Employee> getAllEmployeeswithSortAndPagiDelASC(boolean isEmpDeleted, int offset, int pageSize,
			String field) {
		Page<Employee> employees = empRepository.findByEmpWithDeletedStatus(isEmpDeleted,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return employees;
	}

}
