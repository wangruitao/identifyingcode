package org.template.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String logName;
	private String userPassword;
	private Integer userLevel;
	private Integer userLoc;
	
	private List<Role> roles = new ArrayList<Role>();
	


}