<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>일반게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="${param.keyword }">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>


							<tbody>
								<c:forEach items="${requestScope.map.vo }" var="board"
									varStatus="status">
									<tr>
										<td>${board.no }</td>
										<td class="text-left"><a
											href="${pageContext.request.contextPath }/board/read?no=${board.no}">${board.title }</a></td>
										<td>${board.name }</td>
										<td>${board.hit }</td>
										<td>${board.date }</td>
										<td><a href="">[삭제]</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="paging">
							<ul>
								<c:choose>
									<c:when test="${param.page >= 2}">
										<li><a
											href="${pageContext.request.contextPath }/board/list?keyword=${param.keyword}&page=${param.page-1}">◀</a></li>
									</c:when>
									<c:when test="${param.page == 1 or param.page == null }">
										<li><a href="">◀</a></li>
									</c:when>
								</c:choose>

								<c:forEach items="${requestScope.map.pSize }" var="pSize"
									varStatus="status">
									<c:choose>
										<c:when
											test="${param.page == status.count or param.page == null and status.count == 1}">
											<li class="active"><a
												href="${pageContext.request.contextPath }/board/list?keyword=${param.keyword}&page=${status.count}">${status.count }</a>
										</c:when>
										<c:when test="${param.page != status.count }">
											<li><a
												href="${pageContext.request.contextPath }/board/list?keyword=${param.keyword}&page=${status.count}">${status.count }</a>
										</c:when>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${param.page == requestScope.map.util.pageSize }">
										<li><a href="">▶</a></li>
									</c:when>
									<c:when
										test="${param.page < requestScope.map.util.pageSize or param.page == null }">
										<li><a
											href="${pageContext.request.contextPath }/board/list?keyword=${param.keyword}&page=${param.page+1}">▶</a></li>
									</c:when>
								</c:choose>
							</ul>


							<div class="clear"></div>
						</div>
						<c:choose>
							<c:when test="${sessionScope.user != null }">
								<a id="btn_write"
									href="${pageContext.request.contextPath }/board/writeform">글쓰기</a>
							</c:when>
							<c:when test="${sessionScope.user == null }">
								<a id="btn_write"
									href="${pageContext.request.contextPath }/user/loginform">글쓰기</a>
							</c:when>
						</c:choose>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<div id="footer">Copyright ⓒ 2020 황일영. All right reserved</div>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
