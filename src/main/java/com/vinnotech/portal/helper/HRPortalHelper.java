package com.vinnotech.portal.helper;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.vinnotech.portal.exception.HRPortalException;

public class HRPortalHelper {
	private static final String CLASSNAME = "HRPortalHelper ";
	private static final Logger LOGGER = LoggerFactory.getLogger(HRPortalHelper.class);
	
	public static  File convertMultiPartFileToFile(MultipartFile file) {
		String methodName = "convertMultiPartFileToFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
			LOGGER.info(CLASSNAME + ": Existing from " + methodName);
			return convertedFile;
		}  catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while downloading the file " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}	
	} 

}
