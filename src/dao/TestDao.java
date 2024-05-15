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

	private String baseSql = "select s.NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, IS_ATTEND, t.SUBJECT_CD, t.NO, t.POINT from TEST as t left join STUDENT as s on t.STUDENT_NO = s.NO";

	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = new Test();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=? and CLASS_NUM=?";
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from TEST " + condition);
			// プリペアードステートメントに科目コードをバインド
			statement.setString(1, student.getNo());
			// プリペアードステートメントに学校コードをバインド
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(5, student.getClassNum());
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			test.setStudent(student);
			test.setClassNum(rSet.getString("CLASS_NUM"));
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(no);
			test.setPoint(rSet.getInt("point"));

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
		return test;
	}

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// リストへの格納処理を実行
		while (rSet.next()) {
			Test test = new Test();
			// 学生インスタンスを初期化
			Student student = new Student();

			Subject subject = new Subject();
			// 学生インスタンスに検索結果をセット
			student.setNo(rSet.getString("no"));
			student.setname(rSet.getString("name"));
			student.setEntYear(rSet.getInt("ent_year"));
			student.setClassNum(rSet.getString("class_num"));
			student.setIsAttend(rSet.getBoolean("is_attend"));
			student.setSchool(school);

			SubjectDao subjectDao = new SubjectDao();
			subject= subjectDao.get(rSet.getString("subject_cd"),school);

			// 成績インスタンスに結果をセット
			test.setStudent(student);
			test.setClassNum(rSet.getString("class_num"));
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(rSet.getInt("no"));
			test.setPoint(rSet.getInt("point"));

			list.add(test);
		}
		return list;
	}

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> testSet = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = " where t.SUBJECT_CD=? and t.SCHOOL_CD=? and t.NO=? and t.CLASS_NUM=? and s.ENT_YEAR=?";
		// SQL文のソート
		String order = " order by s.NO asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに科目コードをバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントに学校コードをバインド
			statement.setString(2, school.getCd());
			statement.setInt(3, num);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(4, classNum);
			statement.setInt(5, entYear);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			testSet = postFilter(rSet, school);

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

		return testSet;
	}

	public boolean save(List<Test> list) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;
		// SQL文の条件
		String condition = "where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=?";

		try {

			for (int i = 0; i < list.size(); i++) {
				Test test = new Test();
				test = list.get(i);
				// プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update TEST set POINT=? " + condition);
				// プリペアードステートメントに値をバインド
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());

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