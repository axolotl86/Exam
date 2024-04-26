package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class SubjectUpdateExcuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		// student_create.jspからデータを受け取る
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");
		boolean is_attend = false;

		// バリデーションチェック
				boolean[] errors = {false};
		        if (name == null || name.isEmpty()) {
		            errors[2]=true;
		        }
		        if (errors[2] || errors[3]) {

		            // 入力されたデータとエラーメッセージをリクエストにセット
		        	request.setAttribute("cd", cd);
		            request.setAttribute("name", name);

		            // 入力画面にフォワード
		            RequestDispatcher dispatcher = request.getRequestDispatcher("subject_update.jsp");
		            dispatcher.forward(request, response);
		        }else{


						if(){
							// DB更新が完了した場合
							request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
						}else{
							// DB更新がなかった場合
							request.getRequestDispatcher("subject_update.jsp").forward(request, response);
						}
					}
				}
}