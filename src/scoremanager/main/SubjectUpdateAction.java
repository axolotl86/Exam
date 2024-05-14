package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");


        Subject subject = new Subject();
        request.setAttribute("cd", cd);
		request.setAttribute("name", name);
		System.out.println(name);


        SubjectDao subjectDao = new SubjectDao();

        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
