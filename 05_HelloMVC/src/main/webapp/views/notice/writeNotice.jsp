<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
<%@ include file = "/views/common/header.jsp" %>

<section id="notice-container">
    <h2>공지사항 작성</h2>
    <form action="<%=request.getContextPath()%>/notice/writeNoticeEnd.do" method="post" onsubmit="return noticeCheck();" enctype="multipart/form-data">
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td><input type="text" name="noticeTitle" required></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><input type="text" name="noticeWriter" value="<%=loginMember.getUserId()%>" readonly></td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td><input type="file" name="upFile"></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea name="noticeContent" cols="47" rows="5" required></textarea></td>
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