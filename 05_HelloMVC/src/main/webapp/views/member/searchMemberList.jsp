<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.common.AESEncrypt" %>
<%@ page import="com.web.member.model.vo.Member, java.util.List" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("members");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<style type="text/css">
	div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}

    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
</style>
<%@ include file = "/views/common/header.jsp" %>
	<section id="memberList-container">
       <h2>회원관리</h2>
       <div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<option value="userId" >아이디</option>
        		<option value="userName" >회원이름</option>
        		<option value="gender" >성별</option>
        	</select>
        	<div id="search-userId">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userId" >
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 아이디를 입력하세요" >
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-userName">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 이름을 입력하세요">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-gender">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="M" >남</label>
        			<label><input type="radio" name="searchKeyword" value="F" >여</label>
        			<button type="submit">검색</button>
        		</form>
        	</div>
        </div>
        <div id="numPerpage-container">
        	페이지당 회원수 : 
        	<form id="numPerFrm" action="<%=request.getContextPath()%>/admin/searchMember">
        		<select name="numPerpage" id="numPerpage" onchange="this.form.submit()">
        			<option value="10">10</option>
        			<option value="5" >5</option>
        			<option value="3" >3</option>
        		</select>
        		<input type="hidden" name="searchType" value="<%=request.getParameter("searchType")%>">
        		<input type="hidden" name="searchKeyword" value ="<%=request.getParameter("searchKeyword")%>">
        	</form>
        </div>
       <table id="tbl-member">
           <thead>
               <tr>
                   	<th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
               </tr>
           </thead>
           <tbody>
      	    	<% if(list != null){%>
      	    		<% for(int i=0;i<list.size();i++){ %>
      	    			<tr>
							<td><%=list.get(i).getUserId() %></td>
							<td><%=list.get(i).getUserName() %></td>
							<td><%=list.get(i).getGender() %></td>
							<td><%=list.get(i).getAge() %></td>
							<td><%=list.get(i).getEmail() %></td>
							<td><%=list.get(i).getPhone() %></td>
							<td><%=list.get(i).getAddress() %></td>
							<td><%=String.join(",",list.get(i).getHobby())%></td>
							<td><%=list.get(i).getEnrollDate() %></td>
      	    			</tr>
      	    		<%} %>
      	    	<%} else {%>
      	    		<tr>결과가 없습니다.</tr>
      	    	<%} %>
           </tbody>
       </table>
       <div id="pageBar">
       		<%=request.getAttribute("pageBar") %>
       </div>
	</section>
	<script>
		$(()=>{
			$("#searchType").change((e)=>{
				/* $("div#search-container>div").css("display","none");
				switch($("#searchType").val()){
				case "userId" : $("div#search-userId").css("display","inline-block");break;
				case "userName" : $("div#search-userName").css("display","inline-block");break;
				case "gender" : $("div#search-gender").css("display","inline-block");break;
				}  */
				const type = $(e.target).val();
				$("div#search-container>div").hide();
				$("#search-"+type).css("display","inline-block");
			})	
			$("#numPerpage").val('<%=request.getParameter("numPerpage")!=null?request.getParameter("numPerpage"):"10"%>');
			<%if(searchType != null){%>
				$("div#search-container>#searchType").val("<%=searchType%>");
				$("div#search-container>div").hide();
				$("#search-<%=searchType%>").css("display","inline-block");
				<%if(searchType.equals("userId") || searchType.equals("userName")){%>
					$("#search-<%=searchType%> input[name=searchKeyword]").val("<%=searchKeyword%>");
				<%} else if(searchType.equals("gender")){%>
					$("#search-<%=searchType%> input[name=searchKeyword]:input[value=<%=searchKeyword%>]").prop("checked",true);
				<%}%>
			<%} %>
			console.log($("#search-<%=searchType%> input[name=searchKeyword]:input[value=<%=searchKeyword%>]"));
		})
	</script>
<%@ include file="/views/common/footer.jsp" %>
		