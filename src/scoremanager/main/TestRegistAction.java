package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectCdDao;
import dao.TestNoDao;
import tool.Action;

public class TestRegistAction extends Action {

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();//セッション
			Teacher teacher = (Teacher)session.getAttribute("user");

			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			SubjectCdDao subDao = new SubjectCdDao();//科目Dao
			TestNoDao testNoDao = new TestNoDao();

			boolean done = false;
			//入学年度リスト
			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後まで年をリストに追加
			for (int i= year - 10; i < year + 1; i++) {
				entYearSet.add(i);
			}

			//回数リスト
			// リストを初期化
			List<Integer> noSet = new ArrayList<>();
			// 1～9までをリストに追加


			//DBからデータ取得 3
			// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			List<String> cNumList = cNumDao.filter(teacher.getSchool());
			// ログインユーザーの学校コードをもとに科目一覧を取得
			List<Subject> subList = subDao.filter(teacher.getSchool());
//			//科目名リスト
			noSet=testNoDao.filter(teacher.getSchool());
//			List<String> subName = new ArrayList<>();
//			for(int i = 0; i < subList.size(); i++){
//				subName.add(subList.get(i).getName());
//			}

			//レスポンス値をセット 6
			// リクエストに入学年度をセット
			request.setAttribute("ent_year_set", entYearSet);
			// リクエストにクラス番号をセット
			request.setAttribute("class_num_set", cNumList);
			// リクエストに科目リストをセット
			request.setAttribute("subject_set", subList);
			// リクエストに回数をセット
			request.setAttribute("no_set", noSet);
			// リクエストに検索したかのチェックをセット
			request.setAttribute("done", done);

			// jspへフォワード 7
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}
}
