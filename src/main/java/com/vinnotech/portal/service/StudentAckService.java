package com.vinnotech.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.model.StudentAck;
import com.vinnotech.portal.repository.CourseRepository;
import com.vinnotech.portal.repository.StudentAckRepository;

@Service
public class StudentAckService {
	@Autowired
	private StudentAckRepository studentAckRepository;
	@Autowired
	private CourseRepository courseRepository;

	public void createAck(StudentAck studentAck, Long courseId) {

		Course course = courseRepository.findById(courseId).get();
		int count = course.getCount();
		course.setCount(count + 1);	
		course.getStudentAcks().add(studentAck);
		courseRepository.save(course);
	}

	public List<StudentAck> getAllStudentAcks() {
		return studentAckRepository.findAll();
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(Long studentId, int offset, int pageSize,
			String field) {
		Page<StudentAck> studentAcks = null;
		if (!StringUtils.isEmpty(field)) {
			studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		} else {
			studentAcks = studentAckRepository.findByStudentAckWithCourseIdAndOrder(studentId,
					PageRequest.of(offset, pageSize));

		}
		return studentAcks;
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiASC(Long studentId, int offset, int pageSize,
			String field) {
		Page<StudentAck> studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return studentAcks;
	}

	public void deleteStudentAck(Long id) {
		studentAckRepository.deleteById(id);
	}

}
