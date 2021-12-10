package com.vinnotech.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Suvarna Raju
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Job {
	@Id
	@Column(name = "job_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ref;
	@NotNull
	private String title;
	@Lob
	@Column(length = 1024)
	private String description;
	@NotNull
	private String primarySkill;
	private String secondarySkill;
	@NotNull
	private String experienceLevel;
	private String experience;
	private String contractType;
	private String contractDuration;
	private String salary;
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date postedOn;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;
	private String location;
	@NotNull
	private boolean publish;
	@NotNull
	private String empId;
	private int count;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "job_applied", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "job_ack_id"))
	@JsonIgnore
	private List<JobsAcknowledgement> jobsAcks = new ArrayList<>();

}
