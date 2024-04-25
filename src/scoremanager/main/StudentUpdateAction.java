package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String no = request.getParameter("no");

		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		StudentDao sDao = new StudentDao();
		Student student = new Student();

		student = sDao.get(no);



		// リクエストにデータをセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("student", student);

		// jspへフォワード
		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}
}
