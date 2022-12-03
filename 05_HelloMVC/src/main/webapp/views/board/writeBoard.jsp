<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	section#board-container
	{
		width:600px;
		margin:0 auto;
		text-align:center;
	}
	section#board-container h2
	{
		margin:10px 0;
	}
	table#tbl-board
	{
		width:500px;
		margin:0 auto;
		border:1px solid black;
		border-collapse:collapse;
	}
	table#tbl-board th
	{
		width:125px;
		border:1px solid;
		padding:5px 0;
		text-align:center;
	}
	table#tbl-board td
	{
		border:1px solid;
		padding:5px 0 5px 10px;
		text-align:left;
	}

</style>
<%@ include file = "/views/common/header.jsp" %>

<section id='board-container'>
	<h2>게시판 작성</h2>
	<form action="<%=request.getContextPath()%>/board/writeBoardEnd.do" method="post" onsubmit="return fn_submitChk()">
		<table id='tbl-board'>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle" required>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="boardWriter" value="<%=loginMember.getUserId() %>" readonly>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="upFile">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="boardContent" cols="47" rows="5" required></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록하기">
				</th>
			</tr>
		</table>
	</form>
</section>
<script>
	const fn_submitChk = () => {
		if(!confirm("제출하시겠습니까?")){
			return false;
		}
	}
</script>




<%@ include file="/views/common/footer.jsp" %>