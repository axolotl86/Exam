package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;


public class StudentCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//ローカル変数の宣言
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		//リクエストにデータをセット
		req.setAttribute("class_num_set", list);

		//JSPへフォワード
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}

}
