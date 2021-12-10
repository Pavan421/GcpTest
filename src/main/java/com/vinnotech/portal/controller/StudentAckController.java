package com.vinnotech.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.StudentAck;
import com.vinnotech.portal.service.StudentAckService;

@RestController
@RequestMapping("/api/studentack")
public class StudentAckController {
	@Autowired
	private StudentAckService studentAckService;

	@PutMapping("/{courseId}")
	public void createJobAck(@RequestBody StudentAck studentAck, @PathVariable Long courseId) {
		studentAckService.createAck(studentAck, courseId);
	}

	@GetMapping
	public List<StudentAck> getAllStudentAcks() {
		return studentAckService.getAllStudentAcks();
	}
	
	@GetMapping("/spStudentAckdesc/{studentId}/{offset}/{pageSize}/{field}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		return studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset, pageSize, field);
	}

	@GetMapping("/spStudentAckdesc/{studentId}/{offset}/{pageSize}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize) {
		return studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset, pageSize, "");
	}

	@GetMapping("/spStudentAckasc/{studentId}/{offset}/{pageSize}/{field}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiASC(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		return studentAckService.getAllStudentAckswithSortAndPagiASC(studentId, offset, pageSize, field);
	}

	@DeleteMapping("/{id}")
	public void deleteStudentAck(@PathVariable Long id) {
		studentAckService.deleteStudentAck(id);
	}
}
