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

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, teacher.getSchool());

        request.setAttribute("cd", cd);
		request.setAttribute("name", subject.getName());
//		System.out.println(name);

        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
