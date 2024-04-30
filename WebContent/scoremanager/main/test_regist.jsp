<%-- 成績一覧JSP --%>
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


		<form action="TestRegistExcute.action" method="post">
			<div class="col-4">
				<table>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>科目</th>
						<th>回数</th>
					</tr>
					<tr>
						<td>
							<!-- 入学年度 -->
							<select class="form-select " id="select" name="f1">
								<option value=0>
									--------
								</option>
								<c:forEach var="year" items="${year}">
									<option value="${year}">${year}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- クラス番号 -->
							<select class="form-select " id="select" name="f2">
								<option value=0>
									--------
								</option>
								<c:forEach var="num" items="${class}">
									<option value="${num}">${num}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- 科目名 -->
							<select class="form-select " id="select" name="f3">
								<option value=0>
									--------
								</option>
								<c:forEach var="subject" items="${subject}">
									<option value="${subject}">${subject}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- 回数 -->
							<select class="form-select " id="select" name="f4">
								<option value=0>
									--------
								</option>
								<c:forEach var="subject" items="${count}">
									<option value="${count}">${count}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<c:if test="${errors[0]}">
            		<p style="color: yellow;">入学年度とクラスと科目と回数を選択して下さい</p>
        		</c:if>

        		<button type="submit">検索</button>

			</div>

<%--
			<div class = "col-4">
				<label class="form-label" for="student-no-select">学生番号</label>
				<input type="text" name="no" value="${no}">
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
        			<input type="text" name="name" value="<c:out value="${name}" />">
			</div>
				<c:if test="${errors[2]}">
            		<p style="color: yellow;">氏名を入力して下さい</p>
        		</c:if>
			<div class="col-4">
				<label class="form-label" for="student-class_num-select">クラス</label>
				<select class="form-select " id="student-class_num-select" name="class_num">
					<option value="${clss_num}">
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

--%>
		</form>

		<div class="my-2 text-end px-4">
			<a href="Menu.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
