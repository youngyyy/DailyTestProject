package com.example.domain;

import java.math.BigDecimal;
import java.util.List;

public class Member {

    private Integer memb_id;
    private String memb_no;
    private String name;
	private String department;
    private String email;
    private String password;
    private BigDecimal telephone;
   
    private Integer role_id;  //20150818 添加角色

	public Integer getMemb_id() {
		return memb_id;
	}

	public void setMemb_id(Integer memb_id) {
		this.memb_id = memb_id;
	}

	public String getMemb_no() {
		return memb_no;
	}

	public void setMemb_no(String memb_no) {
		this.memb_no = memb_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getTelephone() {
		return telephone;
	}

	public void setTelephone(BigDecimal telephone) {
		this.telephone = telephone;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
}
