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
    <form action="<%=request.getContextPath()%>/notice/updateNoticeEnd.do" method="post" onsubmit="return noticeCheck();">
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td><input type="text" name="noticeTitle" value="<%=n.getNoticeTitle() %>"></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><input type="text" name="noticeWriter" value="<%=n.getNoticeWriter()%>" readonly></td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td><input type="file" name="upfile" value="<%=n.getFilePath()%>"></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea name="noticeContent" cols="40" rows="8"><%=n.getNoticeContent()%></textarea></td>
        </tr>
        <tr>
            <th colspan="2">
                <input type="submit" value="수정하기" onclick="">
            </th>
        </tr>
    </table>
    <div>
    	<input type="hidden" name="noticeNo" value ="<%=n.getNoticeNo()%>">
    </div>
    </form>
    </div>    
</section>
<script>
	const noticeCheck = () => {
		if($("[name=noticeTitle]").val() == ""){
			alert("제목을 입력해 주세요!!");
			return false;
		}
		else if($("[name=noticeWriter]").val() == ""){
			alert("작성자를 입력해 주세요!!");
			return false;
		}
		else if($("[name=noticeContent]").val() == ""){
			alert("내용 입력해 주세요!!");
			return false;
		}
	}
</script>
<%@ include file="/views/common/footer.jsp" %>