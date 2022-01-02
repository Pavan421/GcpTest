package com.vinnotech.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachments {

	private String panCardPath;
	private String personalNumberPath;
	private String passportPath;
	private String hikeLetterPath;
	private String promotionLatterPath;
	private String resumePath;
	private String photoPath;
}
