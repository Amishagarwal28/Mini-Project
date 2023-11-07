package com.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dto.UserResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity  
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class UserEntity {

	@Id 
    @GeneratedValue()
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String userRole;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "phone_no")
    private Long phoneNo;
    
    @Column(name = "nickname")
    private String nickname;
    
    @Column(name = "dob")
    private Date dob;

    public UserResponseDTO toDto() {
        return UserResponseDTO.builder().userId(userId).userName(userName).email(email).userRole(userRole).build();
    }
}

