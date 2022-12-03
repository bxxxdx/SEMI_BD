<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, com.web.board.model.vo.Board" %>    
<%
	Board b = (Board)request.getAttribute("board");
%>
    
<%@ include file = "/views/common/header.jsp" %>
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
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
			 <%=b.getBoardRenamedFileName()!=null?b.getBoardRenamedFileName():"" %>
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
   
</section>
<script>
	const fn_delete = () => {
		let result = confirm("게시물을 정말 삭제하시겠습니까?");
		if(result) {
			location.replace("<%=request.getContextPath()%>/board/deleteBoard.do?boardNo=<%=b.getBoardNo()%>");			
		}
	}
</script>
<%@ include file="/views/common/footer.jsp" %>