package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		return list;
	}

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSetTest = null;
		// SQL文の条件
		String conditionTest = "where SUBJECT_CD=? and SCHOOL_CD=? and CLASS_NUM=?";
		// SQL文のソート
		String orderTest = " order by NO asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from TEST " + conditionTest + orderTest);
			// プリペアードステートメントに科目コードをバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントに学校コードをバインド
			statement.setString(2, school.getCd());
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(3, classNum);
			// プライベートステートメントを実行
			rSetTest = statement.executeQuery();

			// リストへの格納処理を実行
			while (rSetTest.next()) {
				// 成績インスタンスを初期化
				TestListSubject testLS = new TestListSubject();
				Map<Integer, Integer> map = new HashMap<>();
				map.put(rSetTest.getInt("no"), rSetTest.getInt("point"));
				// プリペアードステートメント
				statement = null;
				// リザルトセット
				ResultSet rSetStudent = null;
				// SQL文の条件
				String conditionStudent = "where NO=? and CLASS_NUM=? and SCHOOL_CD=?";
				try {
					// プリペアードステートメントにSQL文をセット
					statement = connection.prepareStatement("select * from STUDENT " + conditionStudent);
					// プリペアードステートメントに学校コードをバインド
					statement.setString(1, rSetTest.getString("student_cd"));
					statement.setString(2, classNum);
					// プリペアードステートメントに学校コードをバインド
					statement.setString(3, school.getCd());
					// プライベートステートメントを実行
					rSetStudent = statement.executeQuery();

					// 成績インスタンスに結果をセット
					testLS.setEntYear(entYear);
					testLS.setStudentNo(rSetStudent.getString("no"));
					testLS.setStudentName(rSetStudent.getString("name"));
					testLS.setClassNum(classNum);
					testLS.setPoints(map);
					testLS.putPoint(rSetTest.getInt("point"),rSetTest.getInt("no"));

					list.add(testLS);
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


}
