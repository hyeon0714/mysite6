<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1>
				<a href="">MySite-${pageContext.request.contextPath }</a>
			</h1>
			<!-- ${pageContext.request.contextPath } 는 서버의 path명과 같은 이름이 따라온다 서버시작이름이 바껴도 하나만 수정해서 정리된다 -->
			<c:choose>
				<c:when test="${sessionScope.userVo != null }">
					<ul>
						<li>${sessionScope.userVo.name } 님 안녕하세요^^</li>
						<li><a href="/mysite5/user/logout" class="btn_s">로그아웃</a></li>
						<li><a href="/mysite5/user/modifyform" class="btn_s">회원정보수정</a></li>
					</ul>
				</c:when>
				<c:when test="${serssionScope.userVo == null }">
					<ul>
						<li><a href="/mysite5/user/loginform" class="btn_s">로그인</a></li>
						<li><a href="/mysite5/user/joinform" class="btn_s">회원가입</a></li>
					</ul>
				</c:when>
			</c:choose>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="/mysite5/board/list">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="/mysite5/guestbook/addlist">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->