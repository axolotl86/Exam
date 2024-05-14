package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectCdDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.TestNoDao;
import tool.Action;


public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		StudentDao stuDao = new StudentDao();//クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();//科目Dao
		TestDao tesDao = new TestDao();//科目Dao

		// test_regist.jspからデータを受け取る
		String entYearStr=request.getParameter("ent_year");
		String classNum=request.getParameter("class_num");
		String subjectCd=request.getParameter("subject_cd");
		String subjectName=request.getParameter("subject_name");
		String noStr=request.getParameter("no");

    	School school = new School();
    	school = teacher.getSchool();
    	Subject subject = new Subject();
    	subject.setCd(subjectCd);
    	subject.setName(subjectName);
    	subject.setSchool(school);

		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		SubjectCdDao subCdDao = new SubjectCdDao();//科目Dao
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
		List<Subject> subList = subCdDao.filter(teacher.getSchool());
//		//科目名リスト
		noSet=testNoDao.filter(teacher.getSchool());
//		List<String> subName = new ArrayList<>();
//		for(int i = 0; i < subList.size(); i++){
//			subName.add(subList.get(i).getName());
//		}

		//レスポンス値をセット 6
		// リクエストに入学年度をセット
		request.setAttribute("ent_year_set", entYearSet);
		// リクエストにクラス番号をセット
		request.setAttribute("class_num_set", cNumList);
		// リクエストに科目リストをセット
		request.setAttribute("subject_set", subList);
		// リクエストに回数をセット
		request.setAttribute("no_set", noSet);
        request.setAttribute("ent_year", entYearStr);
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject", subject);
    	request.setAttribute("no", noStr);

        // バリデーションチェック
		boolean[] errors ={false, false, false, false};
		if(entYearStr == null || entYearStr.isEmpty()){
			errors[0]=true;
		}
        if (classNum == null || classNum.isEmpty()) {
        	errors[1]=true;
        }
        if (subjectName == null || subjectName.isEmpty()) {
        	errors[2]=true;
        }
        if (noStr == null || noStr.isEmpty()) {
        	errors[3]=true;
        }
        if (errors[0] || errors[1] || errors[2] || errors[3]) {

            // 入力されたデータとエラーメッセージをリクエストにセット
        	request.setAttribute("done", done);

            // 入力画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
            dispatcher.forward(request, response);
        }else{

        	done=true;
        	int entYear = Integer.parseInt(entYearStr);
        	int no = Integer.parseInt(noStr);


        	// 成績インスタンスを初期化
        	TestDao tDao = new TestDao();
        	// 成績インスタンスに検索結果をセット
        	List<Test>testSet=tDao.filter(entYear, classNum, subject, no, school);

    		// リクエストに検索したかのチェックをセット
    		request.setAttribute("done", done);

        	request.setAttribute("test_set", testSet);

        	request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        }
	}

}
