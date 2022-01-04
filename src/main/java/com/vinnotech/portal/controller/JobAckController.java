package com.vinnotech.portal.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vinnotech.portal.helper.HRPortalHelper;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.JobsAcknowledgement;
import com.vinnotech.portal.service.JobAckService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/joback")
public class JobAckController {

	private static final String CLASSNAME = "JobAckController";
	private static final Logger LOGGER = LoggerFactory.getLogger(JobAckController.class);
	@Autowired
	private JobAckService jobAckService;

	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
	private String bucketName;

	/*
	 * @PostMapping("/create/{jobId}") public ResponseEntity<String>
	 * createJobAck(@RequestBody JobsAcknowledgement jobAck,
	 * 
	 * @PathVariable("jobId") Long jobId) { String methodName = "createJobAck";
	 * LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
	 * String createdJobAck = jobAckService.createAck(jobAck, jobId); ; HttpHeaders
	 * header = new HttpHeaders(); header.add("desc",
	 * "creating job acknowledgement"); LOGGER.info(CLASSNAME + ": Existing from  "
	 * + methodName + " method"); return
	 * ResponseEntity.status(HttpStatus.OK).headers(header).body(createdJobAck); }
	 */

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spJobAckdesc/{jobId}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<JobsAcknowledgement>> getAllJobAckswithSortAndPagiDesc(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination and desc sorting");
		Page<JobsAcknowledgement> jobAckSwithSort = jobAckService.getAllJobAckswithSortAndPagiDesc(jobId, offset,
				pageSize, field);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(jobAckSwithSort);

	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spJobAckdesc/{jobId}/{offset}/{pageSize}")
	public ResponseEntity<Page<JobsAcknowledgement>> getAllJobAckswithSortAndPagiDesc(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize) {
		String methodName = "getAllJobAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination and desc sorting");
		Page<JobsAcknowledgement> jobAckSwithSort = jobAckService.getAllJobAckswithSortAndPagiDesc(jobId, offset,
				pageSize, "");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(jobAckSwithSort);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spJobAckasc/{jobId}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<JobsAcknowledgement>> getAllJobAckswithSortAndPagiASC(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination and desc sorting");
		Page<JobsAcknowledgement> jobAckSwithSort = jobAckService.getAllJobAckswithSortAndPagiASC(jobId, offset,
				pageSize, field);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(jobAckSwithSort);
	}

	@PostMapping("/create/{jobId}")
	public ResponseEntity<String> createJobAck(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,

			@RequestParam(value = "emailId") String emailId,

			@RequestParam(value = "phoneNo") long phoneNo,

			@RequestParam(value = "location") String location,

			@RequestParam(value = "country") String country,

			@RequestParam(value = "file") MultipartFile file, @PathVariable("jobId") Long jobId) {
		String methodName = "createJobAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		JobsAcknowledgement jobAck = new JobsAcknowledgement();
		jobAck.setFirstName(firstName);
		jobAck.setLastName(lastName);
		jobAck.setEmailId(emailId);
		jobAck.setPhoneNo(phoneNo);
		jobAck.setLocation(location);
		jobAck.setCountry(country);
		File fileObj = HRPortalHelper.convertMultiPartFileToFile(file);
		// System.out.println("file.getOriginalFilename()" +
		// file.getOriginalFilename());
		String resumePath = firstName + "_" + phoneNo + "." + file.getOriginalFilename().split("\\.")[1];
		s3Client.putObject(new PutObjectRequest(bucketName, resumePath, fileObj));
		fileObj.delete();
		jobAck.setResumePath(resumePath);
		String createdJobAck = jobAckService.createAck(jobAck, jobId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating job acknowledgement");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdJobAck);
	}
}
