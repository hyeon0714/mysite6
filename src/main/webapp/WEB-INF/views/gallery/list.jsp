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
<link href="${pageContext.request.contextPath }/assets/css/gallery.css"
	rel="stylesheet" type="text/css">

<style>
	#addModal{
		width: 100%; /* 가로전체 */
		height: 100%; /* 세로전체 */
		display: none; /* 시작할때 숨김처리 */
		position: fixed; /* 화면에 고정 */
		left: 0; /* 왼쪽에서 0에서 시작 */  
		top: 0;  /* 위쪽에서 0에서 시작 */
		z-index: 999;  /* 제일위에 */
		overflow: auto; /* 내용이 많으면 스크롤 생김 */
		background-color: rgba(0,0,0,0.4); /* 배경이 검정색에 반투명 */  
	}
	
	#addModal .modal-content{
		width: 800px;
		margin: 100px auto;
		padding: 0px 20px 20px 20px;
		border: 1px solid #000000;
		background-color: #ffffff;
	}
	
	#addModal .modal-content .closeBtn{
		margin-left: 783px;
		text-align: right;
		width: 15px;
		color: #aaaaaa;
		font-size: 28px;
		font-weight: bold;
		cursor: pointer;
	}
	
	#viewModal{
		width: 100%; /* 가로전체 */
		height: 100%; /* 세로전체 */
		display: none; /* 시작할때 숨김처리 */
		position: fixed; /* 화면에 고정 */
		left: 0; /* 왼쪽에서 0에서 시작 */  
		top: 0;  /* 위쪽에서 0에서 시작 */
		z-index: 999;  /* 제일위에 */
		overflow: auto; /* 내용이 많으면 스크롤 생김 */
		background-color: rgba(0,0,0,0.4); /* 배경이 검정색에 반투명 */  
	}
	
	#viewModal .modal-content{
		width: 800px;
		margin: 100px auto;
		padding: 0px 20px 20px 20px;
		border: 1px solid #000000;
		background-color: #ffffff;
	}
	
	#viewModal .modal-content .closeBtn{
		margin-left: 783px;
		text-align: right;
		width: 15px;
		color: #aaaaaa;
		font-size: 28px;
		font-weight: bold;
		cursor: pointer;
	}
</style>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>갤러리</h2>
				<ul>
					<li><a href="">일반갤러리</a></li>
					<li><a href="">파일첨부연습</a></li>
				</ul>
			</div>
			<!-- //aside -->
			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->


				<div id="gallery">
					<div id="list">

						<c:choose>
							<c:when test="${sessionScope.userVo != null }">
								<button type="button" id="btnImgUpload">이미지올리기</button>
								<div class="clear"></div>
							</c:when>
							<c:when test="${sessionScope.userVo == null }">
								
							</c:when>
						</c:choose>


						<ul id="viewArea">

							<!-- 이미지반복영역 -->
							<c:forEach items="${requestScope.gList }" var="gList">
							<li>
								<input id="usNo" value=${gList.userNo } >
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath }/upload/${gList.saveName}">
									<div class="imgWriter"	>
										작성자: <strong>${gList.name }</strong>
									</div>
									
								</div>
							</li>
							
							</c:forEach>
							<!-- 이미지반복영역 -->


						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<!-- 이미지등록 팝업(모달)창 -->
	<div id="addModal" class="modal">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath }/gallery/add" method="post" enctype="multipart/form-data">
				<div class="closeBtn">×</div>
				<div class="m-header">간단한 타이틀</div>
				<div class="m-body">
					<div>
						<label class="form-text">글작성</label> <input id="addModalContent"
							type="text" name="content" value="">
					</div>
					<div class="form-group">
						<label class="form-text">이미지선택</label> <input id="file"
							type="file" name="file" value="">
					</div>
				</div>
				<input type="text" name="userNo" value="${sessionScope.userVo.no }">
				<div class="m-footer">
					<button type="submit">저장</button>
				</div>
			</form>
		</div>
	</div>


	<!-- 이미지보기 팝업(모달)창 -->
	<div id="viewModal" class="modal">
		<div class="modal-content">
			<div class="closeBtn">×</div>
			<div class="m-header">간단한 타이틀</div>
			<div class="m-body">
				<div>
					<img id="viewModelImg" src="">
					<!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				<div>
					<p id="viewModelContent"></p>
				</div>
			</div>
			<div class="m-footer">
				<button>삭제</button>
			<input type="text" class="uNo" name="userNo" value="">
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	
	document.addEventListener("DOMContentLoaded", function() {
		
		let modal = document.querySelector("#list");
		modal.addEventListener("click", callModal);
		
		let view = document.querySelector("#viewArea");
		view.addEventListener("click", callview);
		
		let viewModal = document.querySelector("#viewModal")
		viewModal.addEventListener("click", callviewModal);
	});
	
	function callModal() {
		//console.log(event.target.tagName)
		
		if(event.target.tagName == "BUTTON"){
			let addModal = document.querySelector("#addModal");
			
			addModal.style.display = "block";
			
			
			
		}
	}
	
	function callview(){
		
		let usNo = document.querySelector("#usNo");
		console.log(usNo);
		
		if(event.target.tagName == "IMG"){
			let viewModal = document.querySelector("#viewModal");
			
			viewModal.style.display = "block";
			
			let viewImg = document.querySelector("#viewModelImg");
			
			viewImg.src = event.target.src;
			
			let uNo = document.querySelector(".uNo");
			
			
			
		}
	}
	
	function callviewModal() {
		console.log(event.target.className);
		
		if(event.target.className == "closeBtn"){
			let viewModal = document.querySelector("#viewModal");
			
			viewModal.style.display = "none";
		}
	}
	
	
	
</script>

</html>
