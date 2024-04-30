package scoremanager.main;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectCreateExcuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		// subject_create.jspからデータを受け取る
		String code=(request.getParameter("code"));
		String name=request.getParameter("name");

		SubjectDao subDao = new SubjectDao();
		Subject subjectNo = new Subject();
    	School school = new School();
    	school = teacher.getSchool();

		subjectNo = subDao.get(code,school);

        // バリデーションチェック
		boolean[] errors = {false, false, false, false};
        if (code == null || code.isEmpty()) {
            errors[0]=true;
        }
        if (name == null || name.isEmpty()) {
            errors[1]=true;
        }
        if (code.length() != 3) {
            errors[2]=true;
        }
        if(subjectNo!=null){
        	errors[3]=true;
        	System.out.println("tt");
        }
        if (errors[0] || errors[1] || errors[2] || errors[3]) {

            // 入力されたデータとエラーメッセージをリクエストにセット
        	request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.setAttribute("errors", errors);

            // 入力画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("subject_create.jsp");
            dispatcher.forward(request, response);
        }else{

        	boolean count = false;

        	// 学生インスタンスを初期化
        	Subject subject = new Subject();
        	// 学生インスタンスに検索結果をセット
        	subject.setSchool(school);
        	subject.setCd(code);
        	subject.setName(name);


        	// DB更新があった場合、countにはtrueが入る
        	count = subDao.save(subject);

        	if(count){
        		// DB更新が完了した場合
        		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        	}else{
        		// DB更新がなかった場合
        		request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        	}
        }
	}

}
