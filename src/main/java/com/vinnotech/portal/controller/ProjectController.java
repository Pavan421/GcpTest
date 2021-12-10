package com.vinnotech.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Project;
import com.vinnotech.portal.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@PutMapping("/{empId}")
	public void addProject(@RequestBody Project project, @PathVariable Long empId) {
		projectService.createProjectWithEmp(project, empId);
	}

	@PutMapping("/asign/{empId}/{projectId}")
	public void addProject(@PathVariable Long empId, @PathVariable Long projectId) {
		projectService.asignProject(empId, projectId);
	}

	@PutMapping
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}

	@GetMapping("/{projectId}")
	public Project createProject(@PathVariable Long projectId) {
		return projectService.getProject(projectId);
	}

	@GetMapping
	public List<Project> createProject() {
		return projectService.getAllProjects();
	}

	@DeleteMapping("/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		projectService.deleteProject(projectId);
	}
}
