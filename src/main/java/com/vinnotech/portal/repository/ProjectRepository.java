package com.vinnotech.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	/*
	 * @Query(value =
	 * "select * from project inner join employees_projects on employees_projects.project_id=project.project_id where employees_projects.emp_id=?1"
	 * , nativeQuery = true) List<Project> findProjectWithEmpId(Long empId);
	 */

}
