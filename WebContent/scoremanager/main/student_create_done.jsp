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
		<h2 class="h3 mb-3 fw-normalh bg-secondary bg-opacity py-2 px-4" style="background-color:rgba(128, 128, 128, 0.5); text-align: left;">学生情報登録</h2>

		<p class="green-background">登録が完了しました</p>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<ul class="nav">
  			<li class="nav-item"><a href="StudentList.action">学生一覧</a></li>
  			<li class="nav-item" style="margin-left: 10px;"><a href="StudentCreate.action">戻る</a></li>
		</ul>



		</section>
	</c:param>
</c:import>