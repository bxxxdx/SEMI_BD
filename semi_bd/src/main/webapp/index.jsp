<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.centercoordinate.model.vo.CenterCoordinate" %>

<%
	List<CenterCoordinate> list = (List<CenterCoordinate>)request.getAttribute("centerCoordinate");
	if(list != null){
		for(CenterCoordinate cc : list){
			System.out.println(cc);
		}
	}else{
		System.out.println("없당");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방방로지컬띵킹연습</title>
<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/firstpage.css">
</head>
<body>
	<div id="container">
        <div id="leftContainer" style="background-image:url('<%=request.getContextPath()%>/images/firstimage.jpg');">
            <div id= logo>
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="" width="140px" height="100px">
            </div>
        </div>
        <div id="rightContainer">
            <div id="buttonContainer">
                <button>로그인 | 회원가입</button>
            </div>
            <div id="searchContainer">
                <div>어디를 찾고 계신가요?</div>
                <div id="addressContainer">
                	<div>
                		<select name="gu" onchange="getGu();">
                			<option value="-">구 선택</option>
                		</select>
                	</div>
                	&nbsp;&nbsp;&nbsp;
                	<div>
                		<select name="dong">
                			<%if(list!=null){ %>
                				<%for(CenterCoordinate cc : list){ %>
                					<option value = "<%=cc.getDong()%>"><%=cc.getDong()%></option>
                				<%} %>
                			<%} else {%>
                				<option value="-">동 선택</option>
                			<%} %>
                		</select>
                	</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script>
	const gu = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구", "용산구","은평구", "종로구","중구","중랑구"];
	$(()=>{
		for(let i=0;i<gu.length;i++){
			const guOption = $("<option>").val(gu[i]).text(gu[i]);
			$("[name=gu]").append(guOption);
		}	
		<%if(list!=null){%>
			$("[name=gu]").val("<%=list.get(0).getGu()%>")
		<%}else{%>
		
		<%}%>
	})
	const getGu = () =>{
		let gu = $("[name=gu]").first().val();
		location.replace('<%=request.getContextPath()%>/changedong.do?gu='+gu);
		
	}

</script>