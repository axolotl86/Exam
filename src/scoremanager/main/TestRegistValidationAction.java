package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistValidationAction extends Action {

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();//セッション
			Teacher teacher = (Teacher)session.getAttribute("user");

			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			SubjectDao subDao = new SubjectDao();//科目Dao
			TestDao testDao = new TestDao();//科目Dao

			//入学年度リスト
			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後まで年をリストに追加
			for (int i= year - 10; i < year + 1; i++) {
				entYearSet.add(i);
			}

			//回数リスト
			// リストを初期化
			List<Integer> countSet = new ArrayList<>();
			// 1～9までをリストに追加
			for (int i= 1; i < 10; i++) {
				countSet.add(i);
			}

			//DBからデータ取得
			// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			List<String> cNumList = cNumDao.filter(teacher.getSchool());
			// ログインユーザーの学校コードをもとに科目一覧を取得
			List<Subject> subList = subDao.filter(teacher.getSchool());
			//科目名リスト
			List<String> subName = new ArrayList<>();
			for(int i = 0; i < subList.size(); i++){
				subName.add(subList.get(i).getName());
			}

			String entYearStr=request.getParameter("f1");
			String classNum=request.getParameter("f2");
			String subject=request.getParameter("f3");
			String countStr=request.getParameter("f4");

	        // バリデーションチェック
			boolean error1 = false;
	        if (entYearStr == null || entYearStr.isEmpty() || classNum == null || classNum.isEmpty() ||
	        	subject == null || subject.isEmpty() || countStr == null || countStr.isEmpty()) {

	            error1=true;

				//レスポンス値をセット
				// リクエストに入学年度をセット
				request.setAttribute("ent_year_set", entYearSet);
				// リクエストにクラス番号をセット
				request.setAttribute("class_num_set", cNumList);
				// リクエストに科目リストをセット
				request.setAttribute("subject_set", subName);
				// リクエストに回数をセット
				request.setAttribute("count_set", countSet);

	            // 入力されたデータとエラーメッセージをリクエストにセット
	        	request.setAttribute("ent_year", entYearStr);
	            request.setAttribute("class_num", classNum);
	            request.setAttribute("subject", subject);
	    		request.setAttribute("count", countStr);
	            request.setAttribute("error1", error1);

	            // 入力画面にフォワード
	            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
	            dispatcher.forward(request, response);
	        }else{
	        	int entYear = Integer.parseInt(entYearStr);
	        	int count = Integer.parseInt(countStr);

	        	List<Test> testList = testDao.filter(entYear, classNum, subject, count, teacher.getSchool());

				//レスポンス値をセット
				// リクエストに入学年度をセット
				request.setAttribute("ent_year_set", entYearSet);
				// リクエストにクラス番号をセット
				request.setAttribute("class_num_set", cNumList);
				// リクエストに科目リストをセット
				request.setAttribute("subject_set", subName);
				// リクエストに回数をセット
				request.setAttribute("count_set", countSet);

	            // 入力されたデータと成績リストをリクエストにセット
	        	request.setAttribute("ent_year", entYearStr);
	            request.setAttribute("class_num", classNum);
	            request.setAttribute("subject", subject);
	    		request.setAttribute("count", countStr);
	    		request.setAttribute("test", testList);

	            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
	            dispatcher.forward(request, response);

	        }

		}
}
