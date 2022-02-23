package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.repository.RequestQuotRepository;

@Service
public class RequestQuotService {

	private static final String CLASSNAME = "RequestQuotService";
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestQuotService.class);

	@Autowired
	private RequestQuotRepository requestQuotRepository;

	public String createRequestQuot(RequestQuot requestQuot) {
		String methodName = "createRequestQuot";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			requestQuotRepository.save(requestQuot);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "Message Saved Successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating request Quots " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

	public Page<RequestQuot> getAllRequestQuots(int offset, int pageSize) {
		String methodName = "getAllRequestQuots";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<RequestQuot> getAllRequestQuotSort = requestQuotRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "createdDate")));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return getAllRequestQuotSort;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting request Quots " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		}
	}
}
