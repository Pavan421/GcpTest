package com.vinnotech.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.repository.RequestQuotRepository;

@Service
public class RequestQuotService {

	@Autowired
	private RequestQuotRepository requestQuotRepository;

	public RequestQuot createRequestQuot(RequestQuot requestQuot) {
		return requestQuotRepository.save(requestQuot);
	}

	public List<RequestQuot> getAllRequestQuots() {
		return requestQuotRepository.findAll();
	}
}
