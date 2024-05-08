package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectCdDao;
import dao.TestDao;
import dao.TestListStudentDao;
import dao.TestNoDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");



		String studentNo = request.getParameter("student_no");
		System.out.println(studentNo);


		List<TestListStudent> TestListStudentset = new ArrayList<TestListStudent>();


        if (studentNo != null && !studentNo.isEmpty()) {

            try {
                TestDao testDao = new TestDao(); // TestDaoのインスタンスを作成
                StudentDao sDao = new StudentDao();
                Student student = sDao.get(studentNo);
                TestListStudentDao testliststudentDao = new TestListStudentDao();
        		TestListStudentset = testliststudentDao.filter(student);

        		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
        		int year = todaysDate.getYear();//現在の年を取得

        		// 入学年度リストを作成
        		List<Integer> entYearSet = new ArrayList<>();
        		// 10年前から1年後まで年をリストに追加
        		for (int i= year - 10; i < year + 1; i++) {
        			entYearSet.add(i);
        		}

        		// クラスリストを作成
        		ClassNumDao cNumDao = new ClassNumDao();
        		List<String> cNumSet = cNumDao.filter(teacher.getSchool());

        		// 科目リストを作成
        		SubjectCdDao sCdDao = new SubjectCdDao();
        		List<Subject> sCdSet = sCdDao.filter(teacher.getSchool());

        		// テスト回数リストを作成
        		TestNoDao tNoDao = new TestNoDao();
        		List<Integer> tNoSet = tNoDao.filter(teacher.getSchool());

        		request.setAttribute("entYear", entYearSet);
        		request.setAttribute("classNum", cNumSet);
        		request.setAttribute("subject", sCdSet);
        		request.setAttribute("no", tNoSet);


                // リクエスト属性に学生のテストを設定してJSPに転送
                request.setAttribute("testList",TestListStudentset );
                request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 学生番号が提供されていない場合の処理
        }
	}
}
