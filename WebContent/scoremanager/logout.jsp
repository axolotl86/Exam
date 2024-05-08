<%--ログアウトJSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		ログアウト
	</c:param>
<!DOCTYPE html>
<html lang="ja">
<head>
<body>
<h2>ログアウト</h2>
<p><label >ログアウトしました</label></p>
<a href="login.jsp">ログイン</a>
</body>
</head>
</html>
</c:import>