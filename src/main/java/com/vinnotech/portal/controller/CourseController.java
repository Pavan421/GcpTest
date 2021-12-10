package com.vinnotech.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.service.CourseService;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PutMapping
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}

	@GetMapping("/{id}")
	public Course getCourse(@PathVariable Long id) {
		return courseService.getCourse(id);
	}

	@GetMapping
	public List<Course> getAllCourse() {
		return courseService.getAllCourses();
	}

	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
	}

	@GetMapping("/spdesc/{offset}/{pageSize}/{field}")
	private Page<Course> getAllJobswithSortAndPagiDesc(@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		return courseService.getAllCourseswithSortAndPagiDesc(offset, pageSize, field);
	}

	@GetMapping("/spase/{offset}/{pageSize}/{field}")
	private Page<Course> getAllCourseswithSortAndPagiASC(@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		return courseService.getAllCourseswithSortAndPagiASC(offset, pageSize, field);
	}

	@GetMapping("/sppublishdesc/{publish}/{offset}/{pageSize}/{field}")
	private Page<Course> getAllJobswithSortAndPagiDesc(@PathVariable boolean publish, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		return courseService.getAllCourseswithSortAndPagiDesc(publish, offset, pageSize, field);
	}

	@GetMapping("/sppublishasc/{publish}/{offset}/{pageSize}/{field}")
	private Page<Course> getAllJobswithSortAndPagiASC(@PathVariable boolean publish, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		return courseService.getAllCourseswithSortAndPagiASC(publish, offset, pageSize, field);
	}
}
