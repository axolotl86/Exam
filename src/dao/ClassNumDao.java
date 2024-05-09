package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {

	private String baseSql = "select DISTINCT CLASS_NUM from CLASS_NUM where SCHOOL_CD=? order by CLASS_NUM asc";

	public List<String> filter(School school) throws Exception{
		// リストを初期化
		List<String> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			try {
				// リザルトセットを全権走査
				while (rSet.next()) {
					// 学生インスタンスに検索結果をセット
					list.add(rSet.getString("class_num"));
					// リストに追加
				}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
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
