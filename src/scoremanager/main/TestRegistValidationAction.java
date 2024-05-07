package scoremanager.main;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistValidationAction extends Action {

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();//セッション
			Teacher teacher = (Teacher)session.getAttribute("user");

			TestDao testDao = new TestDao();//科目Dao

			String yearStr=request.getParameter("year");
			String classNum=request.getParameter("class_num");
			String subject=request.getParameter("subject");
			String countStr=request.getParameter("count");

	        // バリデーションチェック
			boolean error1 = false;
	        if (yearStr == null || yearStr.isEmpty() || classNum == null || classNum.isEmpty() ||
	        	subject == null || subject.isEmpty() || countStr == null || countStr.isEmpty()) {
	            error1=true;
	            // 入力されたデータとエラーメッセージをリクエストにセット
	        	request.setAttribute("year", yearStr);
	            request.setAttribute("class_num", classNum);
	            request.setAttribute("subject", subject);
	    		request.setAttribute("count", countStr);
	            request.setAttribute("error1", error1);

	            // 入力画面にフォワード
	            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
	            dispatcher.forward(request, response);
	        }else{
	        	int year = Integer.parseInt(yearStr);
	        	int count = Integer.parseInt(countStr);

	        	List<Test> testList = testDao.filter(year, classNum, subject, count, teacher.getSchool());



	        }

		}
}
