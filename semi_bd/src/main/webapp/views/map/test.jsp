<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.centercoordinate.model.vo.CenterCoordinate" %>
<%
	CenterCoordinate cc = (CenterCoordinate)request.getAttribute("cc");
%>
<%@ include file ="/views/common/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/test.css?ver=1">
    <nav>
        
        <div id="searchContainer">
            <div id="rentType" class="option buttonContainer" style="margin-left:10px;">
                <button class="optionButton">월세,전세</button>
                <div class="optionMenu" style="border:1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>거래유형</p>
                        <p>닫기</p>
                    </div>
                    <div><label for=""><input type="checkbox" value="월세" checked> 월세</label></div>
                    <div><label for=""><input type="checkbox" value="전세" checked> 전세</label></div>
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
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;">초기화</button>
                        &nbsp;
                        <button style="width:100px;">적용</button>
                    </div>
                </div>
            </div>
            <div id="roomStructure" class="option buttonContainer" style="margin-left:170px;">
                <button class="optionButton">방 구조</button>
                <div class="optionMenu" style="border: 1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>방 구조</p>
                        <p>닫기</p>
                    </div>
                    <div><label for=""><input type="checkbox" value="오픈형(원룸)" checked> 오픈형(원룸)</label></div>
                    <div><label for=""><input type="checkbox" value="분리형(원룸)" checked> 분리형(원룸)</label></div>
                    <div><label for=""><input type="checkbox" value="복층" checked> 복층</label></div>
                    <div><label for=""><input type="checkbox" value="투룸" checked> 투룸</label></div>
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;">초기화</button>
                        &nbsp;
                        <button style="width:100px;">적용</button>
                    </div>
                </div>
            </div>
            <div id="applianceOption" class="option buttonContainer" style="margin-left:330px;">
                <button class="optionButton">가전옵션</button>
                <div class="optionMenu" style="border: 1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>가전옵션</p>
                        <p>닫기</p>
                    </div>
                    <div><label for=""><input type="checkbox" value="전체" checked> 무관</label><span style="font-size:10px;margin-top:5px;">중복선택이 가능합니다.</span></div>
                    <div><label for=""><input type="checkbox" value="에어컨"> 에어컨 포함</label></div>
                    <div><label for=""><input type="checkbox" value="세탁기"> 세탁기 포함</label></div>
                    <div><label for=""><input type="checkbox" value="냉장고"> 냉장고 포함</label></div>
                    <div><label for=""><input type="checkbox" value="인덕션"> 인덕션 포함</label></div>
                    <div><label for=""><input type="checkbox" value="전자레인지"> 전자레인지 포함</label></div>
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;">초기화</button>
                        &nbsp;
                        <button style="width:100px;">적용</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="gudongContainer"><span style="font-size:20px;">서울시</span>&nbsp;
            <select name="gu" style="width:100px;height:30px;font-size:20px;font-weight:bolder;">
            </select>
            <select name="dong" style="width:100px;height:30px;font-size:20px;font-weight:bolder;">
            </select>
        </div>
    </nav>
	
    <div id="optionContianer"></div>




    
    <section>
        <!-- 지도를 표시할 div 입니다 -->
        <div id="map"></div>

        <!-- 매물 목록을 표시할 div -->
        <div id="listContainer">

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property1.jpg" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>원룸(오픈형)</h3></div>
                    <div>월세 1000/50</div>
                    <div>관리비 5만</div>
                    <div>서울시 관악구 신림동</div>
                </div>
            </div>

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property2.JPG" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>원룸(분리형)</h3></div>
                    <div>월세 1000/55</div>
                    <div>관리비 6만</div>
                    <div>서울시 관악구 봉천동</div>
                </div>
            </div>

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property3.JPG" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>복층형 원룸</h3></div>
                    <div>전세 2억 5천</div>
                    <div>관리비 8만</div>
                    <div>서울시 송파구 잠실동</div>
                </div>
            </div>

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property1.jpg" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>원룸(오픈형)</h3></div>
                    <div>월세 1000/50</div>
                    <div>관리비 5만</div>
                    <div>서울시 관악구 신림동</div>
                </div>
            </div>

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property2.JPG" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>원룸(분리형)</h3></div>
                    <div>월세 1000/55</div>
                    <div>관리비 6만</div>
                    <div>서울시 관악구 봉천동</div>
                </div>
            </div>

            <div class="propertyContainer">
                <div class="propertyImgContainer">
                    <img src="<%=request.getContextPath()%>/upload/property/property3.JPG" alt="">
                </div>
                <div class="propertyDetailContainer">
                    <div><h3>복층형 원룸</h3></div>
                    <div>전세 2억 5천</div>
                    <div>관리비 8만</div>
                    <div>서울시 송파구 잠실동</div>
                </div>
            </div>

    </section>
    
    
    
    
    
    <script>
        let hideFlag = true;
        //옵션 버튼 클릭 이벤트
        $(()=>{
            $(".optionButton").click((e)=>{
                console.log($(e.target).next());
                $(".optionMenu").hide();
                if(hideFlag){
                    $(e.target).next().show();
                    hideFlag = false;
                } else {
                    hideFlag = true;
                }
            });
            

        });


        //구, 동 선택 셀렉-옵션 설정
        const gu = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구", "용산구","은평구", "종로구","중구","중랑구"];
        $(()=>{	
        	drawGu();
            $("[name=gu]").val("<%=cc.getGu()%>");
            $("[name=dong]").val("<%=cc.getDong()%>");
        })

        const drawGu = () => {
            for(let i=0;i<gu.length;i++){
                const guOption = $("<option>").val(gu[i]).text(gu[i]);
                $("[name=gu]").append(guOption);
            }	
        }



    </script>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=622c2a9d3d39799df3c6db829e75db1d"></script>
    <script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(<%=cc.getLatitude()%>, <%=cc.getLongitude()%>), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    
    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption); 
    </script>
    </body>

</html>