<%-- 科目登録JSP --%>
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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">科目情報登録</h2>


		<form action="SubjectCreateExcute.action" method="post">

			<div class = "col-4">
				<label class="form-label" for="subject-code-select">科目コード</label>
				<input type="text" maxlength="3" name="code" value="${code}" required>
			</div>
				<c:choose>
    				<c:when test="${errors[1]}">
						<p style="color: yellow;">科目コードを入力して下さい</p>
    				</c:when>
    				<c:when test="${errors[2]}">
						<p style="color: yellow;">科目コードは3文字で入力してください</p>
    				</c:when>
    				<c:when test="${errors[3]}">
						<p style="color: yellow;">科目コードが重複しています</p>
    				</c:when>

				</c:choose>
			<div class = "col-4">
				<label class="form-label" for="subject-name-select">科目名</label>
        			<input type="text" maxlength="20" name="name" value="${name}" required>
			</div>
				<c:if test="${errors[4]}">
            		<p style="color: yellow;">科目名を入力して下さい</p>
        		</c:if>

		<button type="submit" name="end">登録</button>

		</form>

		<div class="my-2 text-end px-4">
			<a href="StudentList.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
