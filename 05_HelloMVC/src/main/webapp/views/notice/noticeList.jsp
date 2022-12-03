<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, com.web.notice.model.vo.Notice" %>

<%
	List<Notice> list = (List<Notice>)request.getAttribute("notices");
%>    
    
<style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
	table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;} 
</style>
<%@ include file = "/views/common/header.jsp" %>
<section id="notice-container">
       <h2>공지사항</h2>
       <%if(loginMember != null && loginMember.getUserId().equals("admin")){ %>
	       <div style="text-align:right">
	       		<button onclick="location.replace('<%=request.getContextPath()%>/notice/writeNotice.do')">공지작성</button>
	       </div>
       <%} %>
       <table id="tbl-notice">
           <tr>
               <th>번호</th>
               <th>제목</th>
               <th>작성자</th>
               <th>첨부파일</th>
               <th>작성일</th>
           </tr>
           <%if(list != null){ %>
           		<%for(int i=0;i<list.size();i++){ %>
           			<tr>
           				<td><%=list.get(i).getNoticeNo() %></td>
           				<td><a href='<%=request.getContextPath()%>/notice/readNotice.do?noticeNo=<%=list.get(i).getNoticeNo()%>'><%=list.get(i).getNoticeTitle() %></a></td>
           				<td><%=list.get(i).getNoticeWriter() %></td>
           				<td>
							<%if(list.get(i).getFilePath() != null) {%>
								<img src="<%=request.getContextPath()%>/images/file.png" width="20" height="20">
							<%} else { %>
								첨부파일없음
							<%} %>							
						</td>
           				<td><%=list.get(i).getNoticeDate() %></td>
           			</tr>
           		<%} %>
           <%} else {%>
           		<tr>결과가 없습니다..</tr>
           <%} %>
       </table>
       <div id="pageBar">
       		<%=request.getAttribute("pageBar") %>
       </div>
</section>
<%@ include file="/views/common/footer.jsp" %>