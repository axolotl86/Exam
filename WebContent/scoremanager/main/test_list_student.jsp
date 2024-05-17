<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity py-2 px-4">成績管理</h2>
            <form action="TesrListSubjectExecute.action" method="post">
                <div class="row vorder mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="year">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${entYear}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="classNum">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${classNum}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="subject">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subject}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                    <div class="mt-2 text-warning">${error.get("f1")}</div>
                </div>
            </form>
            <c:choose>
                <c:when test="${testList.size() > 0}">
                    <div>氏名: ${student.name}(${student.no})</div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${testList}">
                                <tr>
                                    <td>${test.subjectName}</td>
                                    <td>${test.subjectCd}</td>
                                    <td>${test.num}</td>
                                    <td>${test.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>検索結果が見つかりませんでした。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
