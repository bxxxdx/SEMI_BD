<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.centercoordinate.model.vo.CenterCoordinate" %>
<%
	CenterCoordinate cc = (CenterCoordinate)request.getAttribute("cc");
%>
<%@ include file ="/views/common/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/test.css">
    <nav style="background-color: beige;">
        <div id="searchContainer">
            <div id="rentType" class="option" style="margin-left:10px;">
                <button>월세,전세</button>
                <br>
                <div style="background-color:red;">
                    월세,전세
                </div>
            </div>
            <div id="roomStructure" class="option" style="margin-left:100px;">
                <button>방 구조</button>
                <br>
                <div style="background-color:blue;">
                    방 구조
                </div>
            </div>
            <div id="applianceOption" class="option" style="margin-left:175px;">
                <button>가전옵션</button>
                <br>
                <div style="background-color:green;">
                    가전옵션
                </div>
            </div>
        </div>
        <div id="gudongContainer">서울시
            <select name="gu">
            </select>
            <select name="dong">
            </select>
        </div>
    </nav>
	<div id="optionContianer"></div>
    <!-- 지도를 표시할 div 입니다 -->
    <div id="map" style="width:100%;height:350px;"></div>
    
    
    
    
    <script>
        //옵션 버튼 클릭 이벤트
        $(()=>{
            $(".option").click((e)=>{
                console.log(e.target);
            });
            

        });

    </script>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=622c2a9d3d39799df3c6db829e75db1d"></script>
    <script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    
    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption); 
    </script>
    </body>

</html>