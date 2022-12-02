<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.web.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>

<%@ include file = "/views/common/header.jsp" %>
<section>
	<div id="notice-container">
        <table id="tbl-notice">
	        <tr>
	            <th>제 목</th>
	            <td><%=n.getNoticeTitle() %></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><%=n.getNoticeWriter() %></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td>
	           		<%if(n.getFilePath() != null) {%>
	           		<%= n.getFilePath() %>
	           		<%} %>
	            </td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><%=n.getNoticeContent() %></td>
	        </tr>
	        <%if(loginMember != null && loginMember.getUserId().equals("admin")){ %>
		        <tr>
		            <th colspan="2">
		                <input type="button" value="수정하기" onclick="location.replace('<%=request.getContextPath()%>/notice/updateNotice.do?noticeNo=<%=n.getNoticeNo()%>')">
		                <input type="button" value="삭제하기" onclick="fn_delete();">
		            </th>
		        </tr>
	        <%} %>
    	</table>
    </div>
</section>
<script>
	const fn_delete = () => {
		let result = confirm("게시물을 정말 삭제하시겠습니까?");
		if(result) {
			location.replace("<%=request.getContextPath()%>/notice/deleteNotice.do?noticeNo=<%=n.getNoticeNo()%>");			
		}
	}
</script>
<%@ include file="/views/common/footer.jsp" %>

















