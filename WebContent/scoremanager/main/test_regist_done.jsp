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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績管理</h2>

		<p class="green-background">登録が完了しました</p>

		<div class="my-2 text-start px-4">
			<a href="TestList.action">成績参照</a>
		</div>

		<div class="my-2 text-start px-4">
			<a href="TestRegist.action">戻る</a>
		</div>



		</section>
	</c:param>
</c:import>