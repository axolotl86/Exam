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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績一覧(科目)</h2>
		<form method="get">
			<div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				<div class="col-4">
					<label class="form-label" for="student-f1-select">入学年度</label>
					<select class="form-select " id="student-f1-select" name="year">
						<option value="0">--------</option>
						<c:forEach var="year" items="${entYear }">
							<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
							<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-4">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select " id="student-f2-select" name="classnum">
						<option value="0">--------</option>
						<c:forEach var="num" items="${classNum }">
							<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
							<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-4">
					<label class="form-label" for="student-f3-select">科目</label>
					<select class="form-select " id="student-f2-select" name="subjectname">
						<option value="0">--------</option>
						<c:forEach var="subject" items="${subject }">
							<%-- 現在のnumと選択されていたf3が一致していた場合selectedを追記 --%>
							<option value="${subject.name}" <c:if test="${num==f3}">selected</c:if>>${subject.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-2 text-center">
					<button class="btn btn-secondary" id="filter-button">検索</button>
				</div>
				<div class="mt-2 text--warning">${error.get("f1")}</div>
			</div>
		</form>

		<form class="row">
			<div class="col-md-8">
			<div class="mb-3">
			<label class="form-label" for="student-name-select">学生番号</label>
			<input type="text" class="form-control" name="no" value="${student.no}">
			</div>
			</div>
			<div class="col-md-4">
			<div class="mb-3">
			<label class="invisible">検索</label> <!-- ボタンの高さを合わせるために空のラベルを使用 -->
			<button class="btn btn-secondary" type="submit">検索</button>
			</div>
			</div>
		</form>


				<c:choose>
			<c:when test="${testlistsubject.size()>0}">
				<div>検索結果:${testlistsubject.size()}件</div>
				<table class="table table-hover" >
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<c:forEach var="no" items="${noset}">
						<th>${no}回</th>
						</c:forEach>
					</tr>
					<c:forEach var="testlistsubject" items="${testlistsubject}">
						<tr>
							<td>${testlistsubject.entYear}</td>
							<td>${testlistsubject.classNum}</td>
							<td>${testlistsubject.studentNo}</td>
							<td>${testlistsubject.studentName}</td>
							<c:forEach var="no" items="${noset}">
								<c:choose>
									<c:when test="${testlistsubject.points[no]!=null}">
										<td>${testlistsubject.points[no]}</td>
									</c:when>
									<c:otherwise>
										<td>-</td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>学生情報が存在しませんでした</div>
			</c:otherwise>
		</c:choose>

		</section>
	</c:param>
</c:import>