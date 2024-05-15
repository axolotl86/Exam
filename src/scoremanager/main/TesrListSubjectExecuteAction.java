package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TesrListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//データを受け取る
		String entYear=request.getParameter("entYear");
		String class_num=request.getParameter("class_num");
		String no=request.getParameter("no");
		String subject=request.getParameter("subject");
		boolean is_attend = false;

		   // バリデーションチェック
				boolean[] errors = {false, false, false, false};
		        if (entYear == null || entYear.isEmpty()) {
		            errors[0]=true;
		        }
		        if (class_num == null || class_num.isEmpty()) {
		            errors[1]=true;
		        }
		        if (no == null || no.isEmpty()) {
		            errors[2]=true;
		        }
		        if (subject == null || subject.isEmpty()) {
		            errors[3]=true;
		        }
		        if (errors[0] || errors[1] || errors[2] || errors[3]) {
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

		            // 入力されたデータとエラーメッセージをリクエストにセット
		    		request.setAttribute("entYear", entYear);
		    		request.setAttribute("classNum",class_num);
		    		request.setAttribute("subject",subject);
		    		request.setAttribute("no", no);
		            // 入力画面にフォワード
		            RequestDispatcher dispatcher = request.getRequestDispatcher("subject_update.jsp");
		            dispatcher.forward(request, response);
		        }else{
		        	boolean count = false;
		        	School school = new School();
		    		school = teacher.getSchool();

		    		int entYear1 = Integer.parseInt(entYear);


		    		TestListSubjectDao sDao = new TestListSubjectDao();
		    		List<TestListSubject> testlistsubject = new ArrayList<TestListSubject>();
		    		sDao.filter(entYear1, class_num, subject, school);


		    		// 学生インスタンスに検索結果をセット
		    		testlistsubject.setEntYear(entYear1);
		    		testlistsubject.setClassNum(class_num);
		    		testlistsubject.setSubject(subject);
		    		testlistsubject.setNo(no);


							// DB更新が完了した場合
							request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);

						}
					}
				}