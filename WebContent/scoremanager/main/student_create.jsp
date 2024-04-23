<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">


	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="w-75 text-center m-auto border pb-3">
			<form action = "StudentCreateExecute.action" method="post">
				<div id="wrap_box">
					<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">学生登録情報</h2>
					<c:if test="${errors.size()>0}">
						<div>
							<ul>
								<c:forEach var="error" items="${errors}">
									<li>${error}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<div>
						<!-- 入学年度 -->
						<div>
							<label class="form-label" for="student-year-select">入学年度</label>
							<select class="form-select" id="student-year-select" name="ent_year">
								<%
								   Date dNow = new Date( );
								   SimpleDateFormat ft =
								   new SimpleDateFormat ("yyyy");
								   int ent_year = Integer.parseInt(ft.format(dNow));
								   for (int i = -10; i <= 10; i++){ %>
									<option value=<%=ent_year + i %>><%=ent_year + i %></option>
								<% } %>
							</select>
						</div>
						<!-- 学生番号 -->
						<div>
							<label class="form-label" for="student-no-input">学生番号</label>
							<input class="form-control px-5 fs-5" autocomplete="off"
								id="student-no-input" maxlength="10" name="no" placeholder="学生番号を入力してください"
								style="ime-mode: disabled" type="text" value="${no}" required />
						</div>
						<!-- 氏名 -->
						<div>
							<label class="form-label" for="student-name-input">学生番号</label>
							<input class="form-control px-5 fs-5" autocomplete="off"
								id="student-name-input" maxlength="30" name="name" placeholder="氏名を入力してください"
								style="ime-mode: disabled" type="text" value="${name}" required />
						</div>
						<!-- クラス -->
						<div>
							<label class="form-label" for="student-class-select">クラス</label>
							<select class="form-select" id="student-class-select" name="class_num">
							<% List<String> list= (List<String>)req.getAttribute("class_num_set"); %>
								<c:forEach var="class" items="${list }">
									<option value="${class }">${class }</option>
								</c:forEach>
							</select>
						</div>














					</div>
				</div>
			</form>
			<!-- 戻る -->
			<a href="menu.jsp">戻る</a>
		</section>
	</c:param>
</c:import>