package com.vinnotech.portal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Job;
import com.vinnotech.portal.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public Job createJobNotification(Job job) {
		return jobRepository.save(job);
	}

	public Job getJobNotification(Long id) {
		Optional<Job> job = jobRepository.findById(id);
		// job.orElseThrow(new ValidateException("Record not found"));
		if (job.isPresent()) {
			return job.get();
		}
		throw new ValidateException("Record not Found");
	}

	public void deleteJobNotification(Long id) {
		jobRepository.deleteById(id);
		System.out.println("Job deleted successfully");
	}

	public Page<Job> getAllJobDesc(int offset, int pageSize, String field) {
		return jobRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
	}

	public Page<Job> getAllJobAse(int offset, int pageSize, String field) {
		return jobRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
	}

	public Page<Job> getAllJobswithSortAndPagiDesc(boolean publish, int offset, int pageSize, String field) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new Date());
		String currentDate = sm.format(new Date());
		Page<Job> Jobs = jobRepository.findByJobsWithPublishDate(publish, currentDate,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		return Jobs;
	}

	public Page<Job> getAllJobswithSortAndPagiASC(boolean publish, int offset, int pageSize, String field) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new Date());
		String currentDate = sm.format(new Date());
		Page<Job> Jobs = jobRepository.findByJobsWithPublishDate(publish, currentDate,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return Jobs;
	}

}
