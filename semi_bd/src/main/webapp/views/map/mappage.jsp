<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.centercoordinate.model.vo.CenterCoordinate" %>
<%
	CenterCoordinate cc = (CenterCoordinate)request.getAttribute("cc");
%>
<%@ include file ="/views/common/header.jsp" %>
	<style>
		section{
	        width:100%;
	        height:750px;
	        display:flex;
	    }
	    #mapContainer{
	        width:100%;
	        height:750px;
	    }
	    #listContainer{
	        width:500px;
	        height:750px;
	    }
	    #listContainer{
	        background-color: white;
	    }
	    nav{
	        display:flex;
	        /* justify-content: space-between; */
	    }
	    nav>div{
	        display:flex;
	        
	    }
	    p{
	        margin:0px;
	    }
	    #searchContainer{
	        margin-left:auto;
	    }
	    #searchContainer>button{
	        width:40px;
	        height:40px;
	    }
	    #searchContainer>p{
	        margin-top:9px;
	    }
	    section{
	        position: relative;
	    }
	    #mapContaier{
	        position: absolute;
	    }
	    #layerContainer{
	        position:absolute;
	        width:800px;
	        height: 400px;
	    }
	    /* #layerContainer div{
	        position: absolute;
	    } */
	    .buttonLayer{
	        position: absolute;
	        background-color: white;
	        border:2px solid #D9DFD4;
	        width:230px;
	        height:280px;
	        z-index: 1;
	    }
	    #layerContainer p{
	        font-size:16px;
	        font-weight:bolder;
	    }
	    .buttonLayer>div:first-child>p{
	        font-size:23px;
	        font-weight:bolder;
	        margin-top:8px;
	    }
	    .buttonLayer>div{
	        display:flex;
	        margin-left:10px;
	        margin-right:10px;
	        margin-top:8px;
	    }
	    .buttonLayer>.twoSpace{
	        justify-content: space-between;
	    }
	    #propertyStructure {
	        position:absolute;
	        left:250px;
	    }
	    #applianceOption{
	    	position:absolute;
	    	left:450px;
	    }
	    #layerContainer>.buttonLayer>div:first-child{
	        display: flex;
	        justify-content: space-between;
	    }
	    div.buttonContainer>button.layerBtn{
	    	margin-right:30px;
	    }
	</style>
	<br>
    <nav>
        <div class="buttonContainer">
            <button class="layerBtn" id="rentType" onclick="openLayer(event);"><p>월세, 전세</p></button>
            <button class="layerBtn" id="propertyStructure" onclick="openLayer(event);"><p>방 구조</p></button>
            <button class="layerBtn" id="applianceOption" onclick="openLayer(event);"><p>가전옵션</p></button>
        </div>
        <div id="searchContainer">
            <p style="font-size:20px;font-weight:bolder;vertical-align:middle;">서울시 </p>
            &nbsp;&nbsp;
            <input type="text" placeholder="구 검색" size="10px">
            &nbsp;
            <input type="text" placeholder="법정동 검색" size="10px">
            &nbsp;
            <button><p>Q</p></button>
        </div>
    </nav>
	<section>
	     <div id="layerContainer">
	         <div class="buttonLayer" id="rentType">
	             <div>
	                 <p>거래유형</p>
	                 <p>닫기</p>
	             </div>
	             <div><input type="checkbox" value="월세"><p>월세</p></div>
	             <div><input type="checkbox" value="전세"><p>전세</p></div>
	             <div class="twoSpace">
	                 <div><p>보증금</p></div>
	                 <div><p>전체</p></div>
	             </div>    
	             <div>
	                 <input type="range" min="0" max="10000" step="500" style="width:200px;">
	             </div>
	             <div class="twoSpace">
	                 <div><p>월세</p></div>
	                 <div><p>전체</p></div>
	             </div>    
	             <div>
	                 <input type="range" min="0" max="1000" step="50" style="width:200px;">
	             </div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">초기화</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">적용</button>
	             </div>
	         </div>
	         <div class="buttonLayer" id="propertyStructure">
	             <div>
	                 <p>방 구조</p>
	                 <p>닫기</p>
	             </div>
	             <div><input type="checkbox" value="오픈형(원룸)"><p>오픈형(원룸)</p></div>
	             <div><input type="checkbox" value="분리형(원룸)"><p>분리형(원룸)</p></div>
	             <div><input type="checkbox" value="복층"><p>복층</p></div>
	             <div><input type="checkbox" value="투룸"><p>투룸</p></div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">초기화</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">적용</button>
	             </div>
	         </div>
	         <div class="buttonLayer" id="applianceOption">
	             <div>
	                 <p>가전옵션</p>
	                 <p>닫기</p>
	             </div>
	             <div><input type="checkbox" value="에어컨"><p>에어컨</p></div>
	             <div><input type="checkbox" value="에어컨"><p>세탁기</p></div>
	             <div><input type="checkbox" value="에어컨"><p>냉장고</p></div>
	             <div><input type="checkbox" value="에어컨"><p>인덕션&가스레인지</p></div>
	             <div><input type="checkbox" value="에어컨"><p>전자레인지</p></div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">초기화</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">적용</button>
	             </div>
	         </div>
	         
	     </div>
	     
	     
	     <div id="mapContainer">
	
	     </div>
	     <div id="listContainer">
	
	     </div>
	</section>
	</body>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fc286e48bda3fa00c9aebc43a2b9914e&libraries=services"></script>
    <script>
        var mapContainer = document.getElementById('mapContainer'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(<%=cc.getLatitude()%>, <%=cc.getLongitude()%>), // 지도의 중심좌표
            level: 2 // 지도의 확대 레벨
        };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        const openLayer = (e) => {
            $("div.buttonLayer").css("z-index","10");
            console.log($(e.target).attr('id'));
            switch($(e.target).val()){
            case "rentType" : $("div#rentType").css("z-index","10");break;
            case "propertyStructure" : $("div#propertyStructure").css("z-index","10");break;
            case "applianceOption" : $("div$applianceOption").css("z-index","10");break;
            }
        };

        
        
        
        
	</script>
</html>