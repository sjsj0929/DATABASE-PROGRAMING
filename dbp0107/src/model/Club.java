package model;

import java.util.Date;
import java.util.List;

public class Club {
	private int club_no;
	private int department_no;
	private String club_name;
	private String dept_name;
	private String title;
	private String contents;
	private Date createtime;
	
	public Club() {	}
	
	
	public void update(Club updateClub) {
	    this.title = updateClub.title;
	    this.contents = updateClub.contents;
	}
	
	public Club(int club_no, int department_no, String club_name, String title, String contents, Date createtime) {
		super();
		this.club_no = club_no;
		this.department_no = department_no;
		this.club_name = club_name;
		this.title = title;
		this.contents = contents;
		this.createtime = createtime;
	}

	public Club(String club_name, String title, String contents, Date createtime) {
		super();
		this.club_name = club_name;
		this.title = title;
		this.contents = contents;
		this.createtime = createtime;
	}


	public Club(int club_no, String club_name, String dept_name) {
		super();
		this.club_no = club_no;
		this.club_name = club_name;
		this.dept_name = dept_name;
	}


	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public int getClub_no() {
		return club_no;
	}

	public void setClub_no(int club_no) {
		this.club_no = club_no;
	}


	public int getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}

	
	public String getDept_name() {
		return dept_name;
	}


	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}


	@Override
	public String toString() {
		return "Club [club_no=" + club_no + ", department_no=" + department_no + ", club_name=" + club_name
				+ ", dept_name=" + dept_name + ", title=" + title + ", contents=" + contents + ", createtime="
				+ createtime + "]";
	}


	


}
