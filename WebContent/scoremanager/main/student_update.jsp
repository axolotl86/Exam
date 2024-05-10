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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">学生情報変更</h2>

		<form action="StudentUpdateExcute.action" method="post">
			<div class="col-4">
				<label class="form-label" for="student-ent_year-select">入学年度</label><br>
				<input type="text" name="entYear" value="${student.entYear}" readonly style="border: none;">
			</div>
			<br>
			<div class = "col-4">
				<label class="form-label" for="student-no-select">学生番号</label><br>
				<input type="text" name="no" value="${student.no}" readonly style="border: none;">
			</div>
			<br>
			<div class = "col-4">
				<label class="form-label" for="student-name-select">氏名</label><br>
				<input type="text" name="name" value="${student.name}" style="width: 800px;">
			</div>
			<br>
			<div class="col-4">
				<label class="form-label" for="student-class_num-select">クラス</label>
				<select class="form-select " id="student-class_num-select" name="class_num">
					<option value="${student.classNum}">${student.classNum}</option>
					<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
		</div>

				<div class="col-2 form-check text-center">
    				<label class="form-check-label" for="student-f3-check">在学中
        			<%-- パラメーターf3が存在している場合checkedを追記 --%>
        			<input class="form-check-input" type="checkbox"
               		id="student-isAttend-check" name="isAttend" value="t"
               		<c:if test="${!empty f3}">checked</c:if> />
    				</label>
				</div>
		<button type="submit" name="end">変更して終了</button>

		</form>

		<div class="my-2 text-end px-4">
			<a href="StudentList.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
