package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import dao.TestListStudentDao;
import dao.TestNoDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッションの取得
        Teacher teacher = (Teacher) session.getAttribute("user");
        String studentNo = request.getParameter("student_no");
        System.out.println(studentNo);

        List<TestListStudent> testListStudentSet = new ArrayList<>();

        if (studentNo != null && !studentNo.isEmpty()) {
            try {
                StudentDao sDao = new StudentDao();
                Student student = sDao.get(studentNo);

                if (student != null) {
                    TestListStudentDao testListStudentDao = new TestListStudentDao();
                    testListStudentSet = testListStudentDao.filter(student);

                    LocalDate todaysDate = LocalDate.now(); // 今日の日付を取得
                    int year = todaysDate.getYear(); // 現在の年を取得

                    // 入学年度リストを作成
                    List<Integer> entYearSet = new ArrayList<>();
                    for (int i = year - 10; i <= year; i++) {
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

                    // リクエストに属性を設定
                    request.setAttribute("entYear", entYearSet);
                    request.setAttribute("classNum", cNumSet);
                    request.setAttribute("subject", sCdSet);
                    request.setAttribute("no", tNoSet);
                    request.setAttribute("student", student);
                    // 学生のテストリストを設定
                    request.setAttribute("testList", testListStudentSet);

                    // JSPページに転送
                    request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
                } else {
                    handleError(request, response, "学生が見つかりませんでした");
                }
            } catch (Exception e) {
                e.printStackTrace();
                handleError(request, response, "内部エラーが発生しました");
            }
        } else {
            handleError(request, response, "学生番号を入力してください");
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws Exception {
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test_list.jsp");
        dispatcher.forward(request, response);
    }
}