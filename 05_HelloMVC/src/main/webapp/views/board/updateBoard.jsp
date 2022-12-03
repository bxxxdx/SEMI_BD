<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.board.model.vo.Board" %>
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
<section>
    <div id="board-container">
    <form action="<%=request.getContextPath()%>/board/updateBoardEnd.do" method="post">
        <table id="tbl-board">
	        <tr>
	            <th>제 목</th>
	            <td><input type="text" name="boardTitle" value="<%=b.getBoardTitle()%>" required></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><input type="text" name="boardWriter" value="<%=b.getBoardWriter()%>" readonly></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td><input type="file" name="upfile" value="<%=b.getBoardRenamedFileName()%>"></td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><textarea name="boardContent" cols="40" rows="8"><%=b.getBoardContent()%></textarea></td>
	        </tr>
	        <tr>
	            <th colspan="2">
	                <input type="submit" value="수정하기">
	            </th>
	        </tr>
    	</table>
	    <div>
	    	<input type="hidden" name="boardNo" value ="<%=b.getBoardNo()%>">
	    </div>
    </form>
    </div>    
</section>
<%@ include file="/views/common/footer.jsp" %>