<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, com.web.board.model.vo.Board, com.web.board.model.vo.BoardComment" %>    
<%
	Board b = (Board)request.getAttribute("board");
	List<BoardComment> bcs = (List<BoardComment>)request.getAttribute("boardComment");
%>
    
<%@ include file = "/views/common/header.jsp" %>
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
     div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#3300FF;position:relative;top:-20px;}
        /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
</style>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td><%=b.getBoardNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%=b.getBoardTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=b.getBoardWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=b.getBoardReadCount() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<%if(b.getBoardOriginalFileName() != null) {%>
			 		<img src="<%=request.getContextPath()%>/images/file.png" width="20" onclick="fn_fileDown('<%=b.getBoardRenamedFileName()%>');">&nbsp;<%=b.getBoardRenamedFileName()%>
				<%} else { %>
					첨부파일없음
				<%} %>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><%=b.getBoardContent() %></td>
		</tr>
		<%--글작성자/관리자인경우 수정삭제 가능 --%>
		<%if(loginMember != null && (loginMember.getUserId().equals(b.getBoardWriter()) || loginMember.getUserId().equals("admin"))) {%>
			<tr>
				<th colspan="2">
					  <input type="button" value="수정하기" onclick="location.replace('<%=request.getContextPath()%>/board/updateBoard.do?boardNo=<%=b.getBoardNo()%>')">
			          <input type="button" value="삭제하기" onclick="fn_delete();">
				</th>
			</tr>
		<%} %>
	</table>
	<div id="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/writeBoardComment.do" method="post">
				<textarea name="content" cols="55" rows="3"></textarea>
				<input type="hidden" name="boardref" value="<%=b.getBoardNo()%>">
				<input type="hidden" name="level" value="1">
				<input type="hidden" name="commentref" value="0">;
				<input type="hidden" name="commentWriter" value="<%=loginMember!=null?loginMember.getUserId():""%>">
				<button type="submit" id="btn-insert">등록</button>
			</form>
		</div>
		<table id="tbl-comment">
			<tr class="level1">
   				<td>
				    <sub class="comment-writer"></sub>
				    <sub class="comment-date"></sub>
				    <br>                  
   				</td>
				<td>
				     댓글(로그인한 사용자만), 삭제버튼만들기 (작성자, 관리자만삭제가능)
				</td>
			</tr>
		</table>
	</div>
</section>
<script>
	const fn_delete = () => {
		let result = confirm("게시물을 정말 삭제하시겠습니까?");
		if(result) {
			location.replace("<%=request.getContextPath()%>/board/deleteBoard.do?boardNo=<%=b.getBoardNo()%>");			
		}
	}
	$(() => {
		$(".comment-editor>form>textarea").focus(e=>{
			<%if(loginMember==null){%>
				alert("로그인 후 이용할 수 있습니다.");
				$("#userId").focus();
			<%}%>
		})
		
		
	})
</script>
<%@ include file="/views/common/footer.jsp" %>