package bean;

import java.io.Serializable;

public class Student implements Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;

	public void setNo(String no) {
		this.no = no;
	}
	public String getNo() {
		return no;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public int getEntYear() {
		return entYear;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setIsAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
	public boolean getIsAttend() {
		return isAttend;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public School getSchool() {
		return school;
	}
}
