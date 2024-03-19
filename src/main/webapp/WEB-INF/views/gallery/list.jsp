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
#addModal {
	width: 100%; /* 가로전체 */
	height: 100%; /* 세로전체 */
	display: none; /* 시작할때 숨김처리 */
	position: fixed; /* 화면에 고정 */
	left: 0; /* 왼쪽에서 0에서 시작 */
	top: 0; /* 위쪽에서 0에서 시작 */
	z-index: 999; /* 제일위에 */
	overflow: auto; /* 내용이 많으면 스크롤 생김 */
	background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
}

#addModal .modal-content {
	width: 800px;
	margin: 100px auto;
	padding: 0px 20px 20px 20px;
	border: 1px solid #000000;
	background-color: #ffffff;
}

#addModal .modal-content .closeBtn {
	margin-left: 783px;
	text-align: right;
	width: 15px;
	color: #aaaaaa;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

#viewModal {
	width: 100%; /* 가로전체 */
	height: 100%; /* 세로전체 */
	display: none; /* 시작할때 숨김처리 */
	position: fixed; /* 화면에 고정 */
	left: 0; /* 왼쪽에서 0에서 시작 */
	top: 0; /* 위쪽에서 0에서 시작 */
	z-index: 999; /* 제일위에 */
	overflow: auto; /* 내용이 많으면 스크롤 생김 */
	background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
}

#viewModal .modal-content {
	width: 800px;
	margin: 100px auto;
	padding: 0px 20px 20px 20px;
	border: 1px solid #000000;
	background-color: #ffffff;
}

#viewModal .modal-content .closeBtn {
	margin-left: 783px;
	text-align: right;
	width: 15px;
	color: #aaaaaa;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

#viewModal .modal-content .delete{
	display: none;
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

									<div class="view">
										<img class="imgItem" id="t-${gList.no }"
											src="${pageContext.request.contextPath }/upload/${gList.saveName}" data-user=${gList.userNo } data-no=${gList.no } }>
										<div class="imgWriter">
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

		<input type="hidden" id="session" value="${sessionScope.userVo.no}">
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<!-- 이미지등록 팝업(모달)창 -->
	<div id="addModal" class="modal">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath }/gallery/add"
				method="post" enctype="multipart/form-data">
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
				<input type="hidden" name="userNo" value="${sessionScope.userVo.no }">
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
				<button type="button" class="delete">삭제</button>
				<input type="hidden" class="uNo" name="userNo" value="">
				<input type="hidden" class="galleryNo" name="galleryNo" value="">
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		
		let list = document.querySelector(".imgItem");
		console.log(list.dataset.no);

		let modal = document.querySelector("#list");
		modal.addEventListener("click", callModal);

		let view = document.querySelector("#viewArea");
		view.addEventListener("click", callview);
		
		let addModal = document.querySelector("#addModal")
		addModal.addEventListener("click", calladdModal);

		let viewModal = document.querySelector("#viewModal")
		viewModal.addEventListener("click", callviewModal);
		
		let del = document.querySelector(".delete");
		del.addEventListener("click", dele);
		
	});

	//추가 모달 부르기
	function callModal() {
		//console.log(event.target.tagName)

		if (event.target.tagName == "BUTTON") {
			let addModal = document.querySelector("#addModal");

			addModal.style.display = "block";

		}
	}
	
	//이미지 클릭시 모달 부르기
	function callview() {
		
		//삭제를 위한 no입력
		let no = event.target.dataset.no;
		console.log(no);
		let galleryNo = document.querySelector(".galleryNo");
		galleryNo.value = no;
		
		let del = document.querySelector(".delete");
		
		del.style.display = "none";
		
		let viewModal = document.querySelector("#viewModal");

		viewModal.style.display = "block";

		let viewImg = document.querySelector("#viewModelImg");

		viewImg.src = event.target.src;
		
		let user = event.target.dataset.user;
		
		let uNo = document.querySelector(".uNo");
		uNo.value = user;
		
		let session = document.querySelector("#session")
				
		if(${sessionScope.userVo.no != null} && session.value == uNo.value){
			let del = document.querySelector(".delete");
			del.style.display = "block";
			
		}else{
			
		}
	}
	
	//
	function calladdModal() {
		if (event.target.className == "closeBtn") {
			let addModal = document.querySelector("#addModal");
			
			addModal.style.display = "none";
		}
	} 
	
	//이미지 모달 끄기 
	function callviewModal() {
		//console.log(event.target.className);
		
		let gallery = document.querySelector(".galleryNo")
		
		let galleryNo = gallery.value;

		if (event.target.className == "closeBtn") {
			let viewModal = document.querySelector("#viewModal");
			
			let uNo = document.querySelector(".uNo");
			
			viewModal.style.display = "none";
		}
	}
	
	function dele() {
		console.log("dele")
		
		let non =  document.querySelector(".galleryNo");
		
		console.log(non);
		
		let no = non.value;
		console.log(no);
		
		let galleryVo = {
			no: no
		}
		
		console.log(galleryVo);
		
		axios({
			method: 'delete', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/gallery',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: guestbookVo, //get방식 파라미터로 값이 전달
			data: galleryVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
			})
			.then(function (response) {
			console.log(response); //수신데이타
			
			let num = response.data.no;
			console.log(num);
			
			let tagId ="t-"+num;
			
			console.log(tagId);
			
			console.log(document.querySelector("#"+tagId).parentElement.parentElement)
			
			let removeImg = document.querySelector("#"+tagId)
			
			console.log(removeImg.parentElement.parentElement.parentElement);
			
			removeImg.parentElement.parentElement.remove();
			
			})
			.catch(function (error) {
			console.log(error);
			}); 
		
		
		let viewModal = document.querySelector("#viewModal");
		viewModal.style.display = "none";
	}
	
	
</script>

</html>
