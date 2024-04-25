package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");


		String no = "";
		String name = "";
		String class_num="";
		String entYear="";
		boolean[] errors = {false, false, false, false, false};
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得



		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i= year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}



		// リクエストにデータをセット
		request.setAttribute("no", no);
		request.setAttribute("name", name);
		request.setAttribute("class_num", class_num);
		request.setAttribute("ent_year", entYear);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

		request.setAttribute("errors", errors);

		// jspへフォワード
		request.getRequestDispatcher("student_create.jsp").forward(request, response);
	}
}
