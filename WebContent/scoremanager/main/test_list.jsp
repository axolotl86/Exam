
<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績参照</h2>

		<div class="border rounded p-4 style="border-width: 3px;">
		 <form action="TesrListSubjectExecute.action" method="post">
			<div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				<div class="col">
					<label class="form-label">科目情報</label>
				</div>
				<div class="col">
					<label class="form-label" for="student-f1-select">入学年度</label>
					<select class="form-select" id="student-f1-select" name="year">
						<option value="">--------</option>
						<c:forEach var="year" items="${entYear}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					<c:if test="${errors[0]}">
						<p style="color: #ffd700;">入学年度を指定して下さい</p>
					</c:if>
				</div>
				<div class="col">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="classNum">
						<option value="">--------</option>
						<c:forEach var="num" items="${classNum}">
							<option value="${num}">${num}</option>
						</c:forEach>
					</select>
					<c:if test="${errors[1]}">
						<p style="color: #ffd700;">クラスを指定して下さい</p>
					</c:if>
				</div>
				<div class="col">
					<label class="form-label" for="student-f3-select">科目</label>
					<select class="form-select " id="student-f3-select" name="subject">
						<option value="">--------</option>
						<c:forEach var="subject" items="${subject}">
							<option value="${subject.cd}">${subject.name}</option>
						</c:forEach>
					</select>
					<c:if test="${errors[3]}">
						<p style="color: #ffd700;">科目を指定して下さい</p>
					</c:if>
				</div>
				<div class="col-2 text-center">
					<button type="submit" class="btn btn-secondary" id="filter-button">検索</button>
				</div>
			</div>
		</form>

		<hr> <!-- ここに水平線を追加 -->

		<form action="TestListStudentExecute.action" method="post">
		<div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
			<div class="col-auto">
				<label class="form-label">学生情報</label>
			</div>
			<div class="col">
				<label class="form-label" for="student-name-select">学生番号</label>
				<input type="text" class="form-control" name="student_no">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-secondary" >検索</button>
			</div>
		</div>

				<c:if test="${not empty errorMessage}">
					<p style="color: #ffd700;">${errorMessage}</p>
				</c:if>

		</form>
		</div>

		<p style="color: #4169e1;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
		</section>
	</c:param>
</c:import>
