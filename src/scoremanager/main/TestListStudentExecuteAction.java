package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestNoDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String studentNo = request.getParameter("student_no");

        if (studentNo != null && !studentNo.isEmpty()) {

            try {
                TestDao testDao = new TestDao(); // TestDaoのインスタンスを作成

                // 学生のテストを取得
                List<Test> studentTests = testDao.filter(studentNo); // 学生番号を使って学生のテストを取得するメソッドを仮定

                // リクエスト属性に学生のテストを設定してJSPに転送
                request.setAttribute("testList", studentTests);
                request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 学生番号が提供されていない場合の処理
        }
	}
}
