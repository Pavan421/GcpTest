package com.vinnotech.portal.controller;

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

import com.vinnotech.portal.model.Job;
import com.vinnotech.portal.service.JobService;

@CrossOrigin
@RestController
@RequestMapping("/api/jobs")
public class JobController {

	@Autowired
	private JobService jobService;

	@GetMapping("/{id}")
	public Job getJob(@PathVariable Long id) {
		return jobService.getJobNotification(id);
	}

	@PutMapping
	public Job createJob(@RequestBody Job job) {
		/*
		 * job = Job.builder() .contactPerson("Raju") .description("Java openning ") //
		 * .startDate(LocalDateTime.now()) // .endDate(LocalDateTime.from(new
		 * Date().toInstant()).plusDays(3)) // .updatedDate(LocalDateTime.now())
		 * .experience("2-4 years") .location("Bangalore") .publish(true)
		 * .skills("JAVA") .salary("10k - 20k pm") .title("JAVA Requirement") .build();
		 */
		return jobService.createJobNotification(job);
	}

	@DeleteMapping("/{id}")
	public void deleteJob(@PathVariable Long id) {
		jobService.deleteJobNotification(id);
	}

	@GetMapping("/spdesc/{offset}/{pageSize}/{field}")
	public Page<Job> getAllJobDesc(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		return jobService.getAllJobDesc(offset, pageSize, field);
	}

	@GetMapping("/spase/{offset}/{pageSize}/{field}")
	public Page<Job> getAllJobAse(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		return jobService.getAllJobAse(offset, pageSize, field);
	}

	@GetMapping("/sppublishdesc/{publish}/{offset}/{pageSize}/{field}")
	private Page<Job> getAllJobswithSortAndPagiDesc(@PathVariable boolean publish, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		return jobService.getAllJobswithSortAndPagiDesc(publish, offset, pageSize, field);
	}

	@GetMapping("/sppublishasc/{publish}/{offset}/{pageSize}/{field}")
	private Page<Job> getAllJobswithSortAndPagiASC(@PathVariable boolean publish, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		return jobService.getAllJobswithSortAndPagiASC(publish, offset, pageSize, field);
	}

}
