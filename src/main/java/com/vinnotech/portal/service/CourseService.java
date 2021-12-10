package com.vinnotech.portal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course getCourse(Long id) {
		Optional<Course> course = courseRepository.findById(id);
		// .orElseThrow(new ValidateException("Record not found"));
		if (course.isPresent()) {
			return course.get();
		}
		throw new ValidateException("Record not Found");
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

	public Page<Course> getAllCourseswithSortAndPagiDesc(int offset, int pageSize, String field) {
		Page<Course> courses = courseRepository
				.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		return courses;
	}

	public Page<Course> getAllCourseswithSortAndPagiASC(int offset, int pageSize, String field) {
		Page<Course> courses = courseRepository
				.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return courses;
	}

	public Page<Course> getAllCourseswithSortAndPagiDesc(boolean publish, int offset, int pageSize, String field) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new Date());
		String currentDate = sm.format(new Date());
		Page<Course> Courses = courseRepository.findByCoursesWithPublishDate(publish, currentDate,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		return Courses;
	}

	public Page<Course> getAllCourseswithSortAndPagiASC(boolean publish, int offset, int pageSize, String field) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new Date());
		String currentDate = sm.format(new Date());
		Page<Course> Courses = courseRepository.findByCoursesWithPublishDate(publish, currentDate,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return Courses;
	}
}
