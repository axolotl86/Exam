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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績一覧（学生）</h2>
		<div class="my-2 text-end px-4">

		</div>


				<table class="table table-hover" >
					<tr>
						<th>科目名</th>
						<th>科目コード</th>
						<th>回数</th>
						<th>点数</th>
					</tr>
					<c:forEach var="subject" items="${subjects}">
						<tr>
							<td>${subject.name}</td>
							<td>${subject.cd}</td>
							<td>${subject.cd}"</td>
							<td>${subject.cd}"</td>
						</tr>
					</c:forEach>
				</table>


		</section>
	</c:param>
</c:import>