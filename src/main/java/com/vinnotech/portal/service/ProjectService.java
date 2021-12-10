package com.vinnotech.portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.Project;
import com.vinnotech.portal.repository.EmployeeRepository;
import com.vinnotech.portal.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public void createProjectWithEmp(Project project, Long empId) {

		Employee emp = employeeRepository.findById(empId).get();
		emp.getProjects().add(project);
		employeeRepository.save(emp);
	}

	public void asignProject(Long empId, Long projectId) {
		Employee emp = employeeRepository.findById(empId).get();
		Project project = projectRepository.findById(projectId).get();
		emp.getProjects().add(project);
		employeeRepository.save(emp);
	}

	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	public Project getProject(Long ProjectId) {
		Optional<Project> project = projectRepository.findById(ProjectId);
		if (project.isPresent()) {
			return project.get();
		}
		throw new ValidateException("Record not Found");
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
	}
}
