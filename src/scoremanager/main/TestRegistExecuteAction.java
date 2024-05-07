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


public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		// student_create.jspからデータを受け取る
		String ent_year_str=(request.getParameter("ent_year"));
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String class_num=request.getParameter("class_num");

		StudentDao sDao = new StudentDao();
		Student studentNo = new Student();

		studentNo = sDao.get(no);

        // バリデーションチェック
		boolean[] errors = {false, false, false, false, false};
        if (ent_year_str == null || ent_year_str.isEmpty()) {
            errors[0]=true;
        }
        if (no == null || no.isEmpty()) {
            errors[1]=true;
        }
        if (name == null || name.isEmpty()) {
            errors[2]=true;
        }
        if (class_num == null || class_num.isEmpty()) {
            errors[3]=true;
        }
        if(studentNo!=null){
        	errors[4]=true;
        	System.out.println("tt");
        }
        if (errors[0] || errors[1] || errors[2] || errors[3] || errors[4]) {
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
        	request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", ent_year_str);
            request.setAttribute("class_num", class_num);
            request.setAttribute("class_num_set", list);
    		request.setAttribute("ent_year_set", entYearSet);
            request.setAttribute("errors", errors);

            // 入力画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("student_create.jsp");
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
