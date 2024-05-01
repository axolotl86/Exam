package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "";

	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = new Test();
		return test;
	}

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		return list;
	}

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSetTest = null;
		// SQL文の条件
		String conditionTest = "where SUBJECT_CD=? and SCHOOL_CD=? and NO=? and CLASS_NUM=?";
		// SQL文のソート
		String orderTest = " order by NO asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select STUDENT_CD, POINT from TEST " + conditionTest + orderTest);
			// プリペアードステートメントに科目コードをバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントに学校コードをバインド
			statement.setString(2, school.getCd());
			statement.setInt(3, num);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(4, classNum);
			// プライベートステートメントを実行
			rSetTest = statement.executeQuery();

			// リストへの格納処理を実行
			while (rSetTest.next()) {
				// 成績インスタンスを初期化
				Test test = new Test();
				// プリペアードステートメント
				statement = null;
				// リザルトセット
				ResultSet rSetStudent = null;
				// SQL文の条件
				String conditionStudent = "where NO and SCHOOL_CD=?";
				try {
					// プリペアードステートメントにSQL文をセット
					statement = connection.prepareStatement("select * from STUDENT " + conditionStudent);
					// プリペアードステートメントに学校コードをバインド
					statement.setString(1, rSetTest.getString("student_cd"));
					// プリペアードステートメントに学校コードをバインド
					statement.setString(2, school.getCd());
					// プライベートステートメントを実行
					rSetStudent = statement.executeQuery();
					// 学生インスタンスを初期化
					Student student = new Student();
					// 学生インスタンスに検索結果をセット
					student.setNo(rSetStudent.getString("no"));
					student.setname(rSetStudent.getString("name"));
					student.setEntYear(rSetStudent.getInt("ent_year"));
					student.setClassNum(rSetStudent.getString("class_num"));
					student.setIsAttend(rSetStudent.getBoolean("is_attend"));
					student.setSchool(school);

					// 成績インスタンスに結果をセット
					test.setStudent(student);
					test.setClassNum(classNum);
					test.setSubject(subject);
					test.setSchool(school);
					test.setNo(num);
					test.setPoint(rSetTest.getInt("point"));

					list.add(test);
				} catch (Exception e) {
					throw e;
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

	return list;
	}

	public boolean save(List<Test> list) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;
		// SQL文の条件
		String condition = "where STUDENT_CD=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=?";

		try {

	        for (int i = 0; i < list.size(); i++) {
	            Test test = new Test();
	            test = list.get(i);
	            // プリペアードステートメントにUPDATE文をセット
	            statement = connection.prepareStatement(
					"update TEST set POINT=? " + condition);
			    // プリペアードステートメントに値をバインド
			    statement.setString(1, test.getStudent().getNo());
			    statement.setString(2,  test.getSubject().getCd());
			    statement.setString(3, test.getSchool().getCd());
			    statement.setInt(4, test.getNo());
			    statement.setInt(5,  test.getPoint());
			    // プリペアードステートメントを実行
			    count += statement.executeUpdate();
	        }

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が０件の場合
			return false;
		}
	}

}