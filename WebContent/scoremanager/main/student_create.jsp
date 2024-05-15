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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">学生情報登録</h2>


		<form action="StudentCreateExcute.action" method="post">
			<div class="col-4">
				<label class="form-label" for="student-ent_year-select">入学年度</label>
				<select class="form-select " id="student-ent_year-select" name="ent_year">
					<option value="${ent_year}">
						<c:choose>
    						<c:when test="${empty ent_year}">
								--------
    						</c:when>
    						<c:otherwise>
								${ent_year }
    						</c:otherwise>
						</c:choose>
					</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}">${year}</option>
					</c:forEach>
				</select>
			</div>
				<c:if test="${errors[0]}">
            		<p style="color: yellow;">入学年度を選択して下さい</p>
        		</c:if>
			<div class = "col-4">
				<label class="form-label" for="student-no-select">学生番号</label>
				<input type="text" maxlength="10" name="no" value="${no}" required>
			</div>
				<c:choose>
    				<c:when test="${errors[1]}">
						<p style="color: yellow;">学生番号を入力して下さい</p>
    				</c:when>
    				<c:when test="${errors[4]}">
						<p style="color: yellow;">学生番号が重複しています</p>
    				</c:when>
				</c:choose>
			<div class = "col-4">
				<label class="form-label" for="student-name-select">氏名</label>
        			<input type="text" maxlength="10" name="name" value="<c:out value="${name}" />" required>
			</div>
				<c:if test="${errors[2]}">
            		<p style="color: yellow;">氏名を入力して下さい</p>
        		</c:if>
			<div class="col-4">
				<label class="form-label" for="student-class_num-select">クラス</label>
				<select class="form-select " id="student-class_num-select" name="class_num">
					<option value="${class_num}">
						<c:choose>
    						<c:when test="${empty class_num}">
								--------
    						</c:when>
    						<c:otherwise>
								${class_num }
    						</c:otherwise>
						</c:choose>
					</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}">${num}</option>
					</c:forEach>
				</select>
			</div>
				<c:if test="${errors[3]}">
            		<p style="color: yellow;">クラスを選択して下さい</p>
        		</c:if>
        	<br>
		<button type="submit" name="end">登録して終了</button>

		</form>

		<div class="my-2 text-end px-4">
			<a href="StudentList.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
