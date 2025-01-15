package himedia.myportal.repositories.vo;

import java.util.Date;

public class UserVo {
	private Integer no;
	private String name;
	private String email;
	private String password;
	private String gender;
	private Date joindate;

	// 생성자
	public UserVo() {
		super();
	}

	public UserVo(String name, String email, String password, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public UserVo(Integer no, String name, String email, String password, String gender, Date joindate) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.joindate = joindate;
	}

	// Getters and Setters
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", joindate=" + joindate + "]";
	}

}
