package scoremanager.main;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
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
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String pointStr=request.getParameter("point_"+no);
    	int point = Integer.parseInt(pointStr);

        // バリデーションチェック
		boolean error2 = false;
        if (point < 0 || 100 < point) {
        	error2 = true;

            // 入力されたデータとエラーメッセージをリクエストにセット
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("class_num", classNum);
        	request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("point", pointStr);
            request.setAttribute("error2", error2);

            // 入力画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
            dispatcher.forward(request, response);
        }else{

        	int entYear = Integer.parseInt(entYearStr);

        	// 成績インスタンスを初期化
        	Test test = new Test();
        	// 成績インスタンスに検索結果をセット
        	test.setStudent(stuDao.get(no));
        	test.setClassNum(classNum);
        	test.setSubject(subDao.get(cd, teacher.getSchool()));
        	test.setPoint(point);
        	test.setSchool(teacher.getSchool());


        	// DB更新があった場合、countにはtrueが入る
        	count = tesDao.save(test);

        	if(count){
        		// DB更新が完了した場合
        		request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
        	}else{
        		// DB更新がなかった場合
        		request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        	}
        }
	}

}
