package com.model;


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
public class UserHealthData {
	
	private int userHealthDataId;
	private int userId;
	private String gender;
	private int age;
	private boolean isSmoker;
	private boolean isDrinker;
	private boolean hasTerminalDisease;
	
}
