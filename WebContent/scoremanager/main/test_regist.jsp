<%-- 成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<c:import url="/common/base.jsp" >

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績管理</h2>


		<form action="TestRegistExecute.action" method="post">
			<div class="col-4">
				<table style="border: 2px solid black; border-collapse: separate; border-radius: 20px; overflow: hidden; width: 300%;">
					<tr>
						<th style="padding: 8px;">入学年度</th>
						<th style="padding: 8px;">クラス</th>
						<th style="padding: 8px;">科目</th>
						<th style="padding: 8px;">回数</th>
					</tr>
					<tr>
						<td>
							<!-- 入学年度 -->
							<select class="form-select " id="select" name="ent_year">
								<option value="${ent_year}">
									<c:choose>
			    						<c:when test="${empty ent_year}">
											--------
			    						</c:when>
			    						<c:otherwise>
											${ent_year}
			    						</c:otherwise>
									</c:choose>
								</option>
								<c:forEach var="ent_year" items="${ent_year_set}">
									<option value="${ent_year}" style="padding: 8px;">${ent_year}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- クラス番号 -->
							<select class="form-select " id="select" name="class_num">
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
								<c:forEach var="class_num" items="${class_num_set}">
									<option value="${class_num}" style="padding: 8px;">${class_num}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- 科目名 -->
							<select class="form-select " id="select" name="subject_name">
								<option value="${subject.name}">
									<c:choose>
			    						<c:when test="${empty subject}">
											--------
			    						</c:when>
			    						<c:otherwise>
											${subject.name }
			    						</c:otherwise>
									</c:choose>
								</option>
								<c:forEach var="subject" items="${subject_set}">
									<option value="${subject.name}" style="padding: 8px;">${subject.name}</option>
									<input type="hidden" name="subject_cd" value="${subject.cd }">
								</c:forEach>
							</select>
						</td>
						<td>
							<!-- 回数 -->
							<select class="form-select " id="select" name="no">
								<option value="${count}">
									<c:choose>
			    						<c:when test="${empty count}">
											--------
			    						</c:when>
			    						<c:otherwise>
											${count }
			    						</c:otherwise>
									</c:choose>
								</option>
								<c:forEach var="count" items="${no_set}">
									<option value="${count}" style="padding: 8px;">${count}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<c:if test="${error1}">
            		<p style="color: yellow;">入学年度とクラスと科目と回数を選択して下さい</p>
        		</c:if>
        		<input type="hidden" name="flag" value="t">

        		<button type="submit">検索</button>

			</div>
		</form>

		<c:if test="${done}">
			<c:choose>
			    <c:when test="${test_set.size()>0}">
					<form action="TestRegistExecute.action" method="post">
					    <%-- リストを作成 --%>
		    			<%
		        			List<String> dataList = new ArrayList<>();
		    			%>
						<div class="col-4">
							<h2>科目："${subject.name}"（"${no.now}"回）</h2>
							<table>
								<tr>
									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>点数</th>
								</tr>
								<c:forEach var="test" items="${test_set}">
									<tr>
										<td>"${test.student.entYear }"	<input type="hidden" name="ent_year" value="${test.student.entYear}"></td>
										<td>"${test.student.classNum }" <input type="hidden" name="class_num" value="${test.student.classNum }"></td>
										<td>"${test.student.no}" <input type="hidden" name="no" value="${test.student.no}"></td>
										<td>"${test.student.name}" <input type="hidden" name="name" value="${test.student.name}"></td>
										<td>"${test.point}" <input type="text" name="point" value="${point}">
										<div>
											<c:if test="${error2}">
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
				</c:when>
				<c:otherwise>
					<p>存在しません</p>
				</c:otherwise>
			</c:choose>
		</c:if>

		<div class="my-2 text-end px-4">
			<a href="Menu.action">戻る</a>
		</div>


		</section>
	</c:param>
</c:import>
