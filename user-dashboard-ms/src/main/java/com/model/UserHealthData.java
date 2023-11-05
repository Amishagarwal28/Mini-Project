package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class UserHealthData {
	@Id
	@GeneratedValue
	private int userHealthDataId;
	private int userId;
	private String gender;
	private int age;
	private boolean isSmoker;
	private boolean isDrinker;
	private boolean hasTerminalDisease;
	
}
