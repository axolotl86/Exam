package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

	/**
	 * 学校コード:String
	 */
	private School school;

	/**
	 * クラス番号:String
	 */
	private String classNum;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

}
