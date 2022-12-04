<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>방방(방 구해줘~방)</title>

<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
<script src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?622c2a9d3d39799df3c6db829e75db1d"></script>
</head>
<style>
    header{
        display: flex;
        justify-content: space-between;
    }
    div.menu{
        font-size:28px;
        font-weight:bolder;
        vertical-align:middle;
        margin-top:45px;
    }
    .buttonContainer>button{    
        width:150px;
        height:40px;     
        background-color: #075A2A;
        color:white;
        border:0px;
    }
    .buttonContainer>button>p{
        font-weight: bolder;
        font-size:17px;
    }
    #loginBtn{
        margin-top:20px;
        margin-right:20px;
    }
</style>
<body bgcolor = "#D9DFD4";>
   <header>
        <div id="logoContainer">
            <img src="<%=request.getContextPath()%>/images/logo.png" alt="" width="140px" height="100px">
        </div>
        <div class="menu">
            <p>지도</p>
        </div>
        <div class="menu">
            <p>마이페이지</p>
        </div>
        <div class="menu">
            <p>방내놓기</p>
        </div>
        <div class="menu">
            <p>중개사 등록</p>
        </div>
        <div class="buttonContainer" id="loginBtn">
            <button><p>로그인 | 회원가입</p></button>
        </div>
    </header>