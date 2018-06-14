package com.mazurak.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher_details")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Getter @Setter
public class TeacherDetails  extends BaseEntity {
	
	private String email;

	private String hobby;

}
