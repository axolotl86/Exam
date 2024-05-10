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
				<c:if test="${errors[2]}">
            		<p style="color: yellow;">氏名を入力して下さい</p>
        		</c:if>
			</div>
			<br>
			<div class="col-4">
				<label class="form-label" for="student-class_num-select">クラス</label>
				<select class="form-select" id="student-class_num-select" name="class_num" style="width: 800px;">
    				<option value="${student.classNum}">${student.classNum}</option>
    				<c:forEach var="num" items="${class_num_set}">
        				<option value="${num}">${num}</option>
    				</c:forEach>
				</select>
				<c:if test="${errors[3]}">
        			<p style="color: yellow;">クラスを選択して下さい</p>
    			</c:if>
			</div>
			<br>

			<div>
    			<label class="form-check-label" for="student-isAttend-check">在学中</label>
    			<input class="form-check-input" type="checkbox" id="student-isAttend-check" name="isAttend" value="t">
			</div>
			<br>
			<button type="submit" name="end" style="background-color: blue; color: white;">変更</button>
			<br>
		</form>

		<div>
			<a href="StudentList.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
