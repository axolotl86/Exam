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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">科目情報変更</h2>

		<form action="SubjectUpdateExcute.action" method="post">
				<div class="col-4">
					<label class="form-label" for="subject-cd">科目コード</label>
					<input type="hidden" id="subject-cd-select" name="cd" value="${subject.code }">
					<p>${subject.code }</p>
				</div>
		<div class = "col-4">
			<label class="form-label" for="subject-name-select">科目名</label>
			<input type="text" name="name" value="${subject.name }">
		</div>
		<button type="submit" name="end" value=40>変更</button>

		</form>

		<div class="my-2 text-end px-4">
			<a href="SubjectList.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>