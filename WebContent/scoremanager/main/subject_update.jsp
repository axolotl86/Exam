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
		<h2 class="h3 mb-3 fw-normalh bg-secondary bg-opacity py-2 px-4" style="background-color:rgba(128, 128, 128, 0.5); text-align: left;">科目情報変更</h2>

		<form action="SubjectUpdateExcute.action" method="post">
				<div class="col-4">
					<label class="form-label" for="subject-cd">科目コード</label><br>
					<input type="text" name="cd" value="${cd}" readonly style="border: none;">
				</div>
		<div class = "col-4">
			<label class="form-label" for="subject-name-select">科目名</label>
			<input type="text" maxlength="20" name="name" value="${name }" required style="border-radius: 5px; width: 500px;">
		</div>
		<br>
		<button type="submit" name="end" value=40 style="background-color: blue; color: white; border-radius: 5px;">変更</button>

		</form>
		<br>
		<a href="SubjectList.action">戻る</a>


		</section>
	</c:param>
</c:import>