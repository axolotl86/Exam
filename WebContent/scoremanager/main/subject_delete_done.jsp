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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">科目情報削除</h2>


			<div class="bg-success bg-opacity-50 p-3 text-center text-dark">
                削除が完了しました
            </div>


			<div class="my-2 text-end px-4">
				<a href="SubjectList.action">科目一覧</a>
			</div>


		</section>
	</c:param>
</c:import>