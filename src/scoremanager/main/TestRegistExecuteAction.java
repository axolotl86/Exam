package scoremanager.main;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import tool.Action;


public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		// test_regist.jspからデータを受け取る
		String entYearStr=request.getParameter("ent_year");
		String classNum=request.getParameter("class_num");
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String pointStr=request.getParameter("point_"+no);
    	int entYear = Integer.parseInt(entYearStr);
    	int point = Integer.parseInt(pointStr);

        // バリデーションチェック
		boolean error2 = false;
        if (point < 0 || 100 < point) {
        	error2 = true;

            // 入力されたデータとエラーメッセージをリクエストにセット
        	request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", ent_year_str);
            request.setAttribute("class_num", class_num);
            request.setAttribute("class_num_set", list);
    		request.setAttribute("ent_year_set", entYearSet);
            request.setAttribute("errors", errors);

            // 入力画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("test_regist.jsp");
            dispatcher.forward(request, response);
        }else{




        	int ent_year=Integer.parseInt(ent_year_str);
        	boolean is_attend=true;
        	boolean count = false;
        	School school = new School();
        	school = teacher.getSchool();


        	// 学生インスタンスを初期化
        	Student student = new Student();
        	// 学生インスタンスに検索結果をセット
        	student.setNo(no);
        	student.setname(name);
        	student.setEntYear(ent_year);
        	student.setClassNum(class_num);
        	student.setIsAttend(is_attend);
        	student.setSchool(school);

        	// DB更新があった場合、countにはtrueが入る
        	count = sDao.save(student);




        	if(count){
        		// DB更新が完了した場合
        		request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
        	}else{
        		// DB更新がなかった場合
        		request.getRequestDispatcher("student_create.jsp").forward(request, response);
        	}
        }
	}

}
