package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッション情報を取得
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		//フォワード
		req.getRequestDispatcher("logout.jsp").forward(req, res);

	}

}
