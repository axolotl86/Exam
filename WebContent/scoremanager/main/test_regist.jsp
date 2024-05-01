<%-- 成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp" >
<%@ page import ="java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績管理</h2>


		<form action="TestRegist.action" method="post">
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
		</form>

		<form action="TestRegistExcute.action" method="post">
			<div class="col-4">
				<h2>科目："${class }"（"${count }"回）</h2>
				<table>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>点数</th>
					</tr>
					<c:forEach var="test" items="${test }">
						<tr>
							<td>"${ent_year }"</td>
							<td>"${class }"</td>
							<td>"${no }"</td>
							<td>"${name }"</td>
							<td>
							<input type="text" name="point_${no }">
							<div>
								<c:if test="${errors[0]}">
	            					<p style="color: yellow;">0～100の範囲で入力してください</p>
	        					</c:if>
							</div>
							</td>
						</tr>
					</c:forEach>
				</table>
				<button type="submit">登録して終了</button>
			</div>
		</form>

		<div class="my-2 text-end px-4">
			<a href="Menu.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
