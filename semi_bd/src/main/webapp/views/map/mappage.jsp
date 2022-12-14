<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/views/common/header.jsp" %>
<%@ page import="com.centercoordinate.model.vo.CenterCoordinate" %>
<%
	CenterCoordinate cc = (CenterCoordinate)request.getAttribute("cc");
%>
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
	        z-index: 10;
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
            <button class="layerBtn" id="rentType" onclick="openLayer(event);"><p>??????, ??????</p></button>
            <button class="layerBtn" id="propertyStructure" onclick="openLayer(event);"><p>??? ??????</p></button>
            <button class="layerBtn" id="applianceOption" onclick="openLayer(event);"><p>????????????</p></button>
        </div>
        <div id="searchContainer">
            <p style="font-size:20px;font-weight:bolder;vertical-align:middle;">????????? </p>
            &nbsp;&nbsp;
            <input type="text" placeholder="??? ??????" size="10px">
            &nbsp;
            <input type="text" placeholder="????????? ??????" size="10px">
            &nbsp;
            <button><p>Q</p></button>
        </div>
    </nav>
	<section>
	     <!-- <div id="layerContainer">
	         <div class="buttonLayer" id="rentType">
	             <div>
	                 <p>????????????</p>
	                 <p>??????</p>
	             </div>
	             <div><input type="checkbox" value="??????"><p>??????</p></div>
	             <div><input type="checkbox" value="??????"><p>??????</p></div>
	             <div class="twoSpace">
	                 <div><p>?????????</p></div>
	                 <div><p>??????</p></div>
	             </div>    
	             <div>
	                 <input type="range" min="0" max="10000" step="500" style="width:200px;">
	             </div>
	             <div class="twoSpace">
	                 <div><p>??????</p></div>
	                 <div><p>??????</p></div>
	             </div>    
	             <div>
	                 <input type="range" min="0" max="1000" step="50" style="width:200px;">
	             </div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">?????????</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">??????</button>
	             </div>
	         </div>
	         <div class="buttonLayer" id="propertyStructure">
	             <div>
	                 <p>??? ??????</p>
	                 <p>??????</p>
	             </div>
	             <div><input type="checkbox" value="?????????(??????)"><p>?????????(??????)</p></div>
	             <div><input type="checkbox" value="?????????(??????)"><p>?????????(??????)</p></div>
	             <div><input type="checkbox" value="??????"><p>??????</p></div>
	             <div><input type="checkbox" value="??????"><p>??????</p></div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">?????????</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">??????</button>
	             </div>
	         </div>
	         <div class="buttonLayer" id="applianceOption">
	             <div>
	                 <p>????????????</p>
	                 <p>??????</p>
	             </div>
	             <div><input type="checkbox" value="?????????"><p>?????????</p></div>
	             <div><input type="checkbox" value="?????????"><p>?????????</p></div>
	             <div><input type="checkbox" value="?????????"><p>?????????</p></div>
	             <div><input type="checkbox" value="?????????"><p>?????????&???????????????</p></div>
	             <div><input type="checkbox" value="?????????"><p>???????????????</p></div>
	             <div class="buttonContainer">
	                 <button style="width:100px;">?????????</button>
	                 &nbsp;&nbsp;
	                 <button style="width:100px;">??????</button>
	             </div>
	         </div> 
	         
	     </div> -->
	     
	     
	     <div id="mapContainer">
	
	     </div>
	     <div id="listContainer">
	
	     </div>
	</section>
	</body>
	<script>
		var mapContainer = document.getElementById('mapContainer'), // ????????? ????????? div 
			mapOption = { 
				center: new kakao.maps.LatLng(33.450701, 126.570667), // ????????? ????????????
				level: 3 // ????????? ?????? ??????
			}; 

		// ????????? ????????? div???  ?????? ????????????  ????????? ???????????????
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// let flag = false;
		// $("div.buttonLayer").hide();
        // const openLayer = (e) => {
		// 	if(flag){
		// 		$("div.buttonLayer").hide();
		// 	}else{
		// 		$("div.buttonLayer").show();
		// 	}
		// 	flag = !flag;
        //     console.log($(e.target).attr('id'));
        //     switch($(e.target).val()){
        //     case "rentType" : $("div#rentType").show();break;
        //     case "propertyStructure" : $("div#propertyStructure").show();break;
        //     case "applianceOption" : $("div$applianceOption").show();break;
        //     }
        // };

        
        
        
        
	</script>
</html>