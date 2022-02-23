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
import com.vinnotech.portal.model.ContactUS;
import com.vinnotech.portal.repository.ContactusRepository;

@Service
public class ContactusService {
	private static final String CLASSNAME = "ContactusService";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactusService.class);

	@Autowired
	private ContactusRepository contactusRepository;

	public ContactUS addContactus(ContactUS contactus) {
		String methodName = "addContactus";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			return contactusRepository.save(contactus);
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

	public Page<ContactUS> getAllContactus(int offset, int pageSize) {
		String methodName = "getAllContactus";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<ContactUS> getAllContactusSort = contactusRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "createdDate")));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return getAllContactusSort;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		}
	}
}
