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
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">

<style>
	.modal{
		display: none;
	}
	.modal .modal-content{
		width: 800px;
		border: 1px solid #000000;
	}
</style>
</head>

<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/mysite5/guestbook/add" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72"
											rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnAdd" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>

					</form>
					
					<!-- 모달 창 컨텐츠 -->
	               <div id="myModal" class="modal">
	                  <div id="guestbook" class="modal-content">
	                     <div class="closeBtn">×</div>
	                     <div class="m-header">
	                        패스워드를 입력하세요
	                     </div>
	                     <div class="m-body">
	                        <input type="password" name="pw" value=""><br>
	                        <input type="text" name="no" value="">
	                     </div>
	                     <div class="m-footer">
	                        <button class="btnDelete" type="button">삭제</button>
	                     </div>
	                  </div>
	               </div>
					
					<div id="list">
					</div><!-- js render가 들어갈 공간 -->
					<!-- 
					<c:forEach items="${requestScope.gList }" var="gList" varStatus="status">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${status.count }</td>
							<td>${gList.name }</td>
							<td>${gList.date }</td>
							<td><a href="/mysite5/guestbook/deleteform?no=${gList.no }">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${gList.content }</td>
						</tr>
					</table>
					</c:forEach>
					<!-- //guestRead -->


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
	<!-- //wrap -->

</body>

<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>

	document.addEventListener("DOMContentLoaded", function(){
		
		//리스트 요청(데이터만)
		
		axios({			//axios 사용
			method: 'get', // put, post, delete 
			url: '${pageContext.request.contextPath }/api/guestbooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: guestbookVo, //get방식 파라미터로 값이 전달
			//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
		})
		.then(function (response) {
			console.log(response); //수신데이타
			
			//리스트 자리에 글을 추가
			for(let i = 0; i<response.data.length; i++){
				let guestVo = response.data[i]
				render(guestVo, "down");	//1개의 글을 렌더에 전달  --> 렌더는 리스트 위치에 그린다
			}
			
			
		})
		.catch(function (error) {
			console.log(error);
		});
		

		
		//등록 요청
		
		let btnAdd = document.querySelector("#btnAdd");
		btnAdd.addEventListener("click", function(){
			event.preventDefault();
			console.log("글쓰기");
			
			let name = document.querySelector("[name='name']").value;
			let password = document.querySelector("[name='password']").value;
			let content = document.querySelector("[name='content']").value;
			
			let guestVo = {
					name: name,
					password: password,
					content: content
			}
			
			axios({			//axios 사용
				method: 'post', // put, post, delete ->포스트는 저장
				url: '${pageContext.request.contextPath }/api/guestbooks',
				headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
				params: guestVo, //get방식 파라미터로 값이 전달
				//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
				responseType: 'json' //수신타입
			})
			.then(function (response) {
				console.log(response); //수신데이타
				
				//리스트 자리에 글을 추가
				let gVo = response.data;
				
				render(gVo, "up");
				
			})
			.catch(function (error) {
				console.log(error);
			});
			
			
			
		});
		
		//모달을 이용해서 삭제 기능(삭제폼)
		let modal = document.querySelector("#list");
		
		modal.addEventListener("click", function(event) {
			console.log(event.target);
						
			if(event.target.tagName == "BUTTON"){
				console.log("버튼");
				
				let modal = document.querySelector(".modal");
				modal.style.display = "block";
				
				let no = document.querySelector("[name=no]");
				
				console.log(no);
				
				no.value = event.target.dataset.no
				
			}
		});
		
		//모달을 이용해서 삭제 기능(삭제)
		let btnDelete = document.querySelector(".btnDelete");
		
		btnDelete.addEventListener("click", function() {
			
			event.preventDefault();
			
			console.log(btnDelete);
			
			let no = document.querySelector("[name=no]").value;
			let password = document.querySelector("[name=pw]").value;
			
			console.log(no);
			console.log(password);
			
			let guestVo = {
				no: no,
				password: password
			}
			
			console.log(guestVo);
			
			axios({			//axios 사용
				method: 'delete', // put, post, delete ->포스트는 저장
				url: '${pageContext.request.contextPath }/api/guestbooks',
				headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
				params: guestVo, //get방식 파라미터로 값이 전달
				//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
				responseType: 'json' //수신타입
			})
			.then(function (response) {
				console.log(response); //수신데이타
				
				let deleteNo = response.data.no
				
				modalDelete(deleteNo);
			})
			.catch(function (error) {
				console.log(error);
			});
			
		});
		
		
		
		
		
		//함수
		function render(guestVo, dir){
			
			let no = guestVo.no
			let name = guestVo.name;
			let date = guestVo.date;
			let content = guestVo.content
			
			
			let list = document.querySelector("#list");
			
			let str = '';
			str += '<table class="guestRead">';
			str += '<colgroup>';
			str += '<col style="width: 10%;">';
			str += '<col style="width: 40%;">';
			str += '<col style="width: 40%;">';
			str += '<col style="width: 10%;">';
			str += '</colgroup>';
			str += '<tr>';
			str += '<td id="na">'+no+'</td>';
			str += '<td>'+name+'</td>';
			str += '<td>'+date+'</td>';
			str += '<td><button type="button" class="btnModal" data-no='+no+'>삭제</button></td>';
			str += '</tr>';
			str += '<tr>';
			str += '<td colspan=4 class="text-left">'+content+'</td>';
			str += '</tr>';
			str += '</table>';
			
			if(dir == "down"){
				list.insertAdjacentHTML("beforeend", str);
			}else if(dir == "up"){
				list.insertAdjacentHTML("afterbegin", str);
			}
			
		}
		
		function modalDelete(deleteNo) {
			let list = document.querySelector("#na");
			console.log(list.textContent);
			
			let no = list.textContent;
			
			let a = document.querySelector(".guestRead");
			
			console.log(a);
			
			if(deleteNo == no){
				a.remove();
			}else{
				
			}
			
		}
		
		
		
		
	});
</script>


</html>