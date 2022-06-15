
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh toán thành công</title>
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
</head>
<style>
    body {
        text-align: center;
        padding: 40px 0;
        background: #EBF0F5;
    }
    h1 {
        color: #006a31;
        font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
        font-weight: 900;
        font-size: 40px;
        margin-bottom: 10px;
    }
    p {
        color: #404F5E;
        font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
        font-size:20px;
        margin: 0;
    }
    i {
        color: #006a31;
        font-size: 100px;
        line-height: 200px;
        margin-left:-15px;
    }
    .card {
        background: white;
        padding: 60px;
        border-radius: 4px;
        box-shadow: 0 2px 3px #C8D0D8;
        display: inline-block;
        margin: 0 auto;
    }
    .bn632-hover {
        width: 160px;
        font-size: 16px;
        font-weight: 600;
        color: #fff;
        cursor: pointer;
        margin: 20px;
        height: 55px;
        text-align:center;
        border: none;
        background-size: 300% 100%;
        border-radius: 50px;
        moz-transition: all .4s ease-in-out;
        -o-transition: all .4s ease-in-out;
        -webkit-transition: all .4s ease-in-out;
        transition: all .4s ease-in-out;
    }

    .bn632-hover:hover {
        background-position: 100% 0;
        moz-transition: all .4s ease-in-out;
        -o-transition: all .4s ease-in-out;
        -webkit-transition: all .4s ease-in-out;
        transition: all .4s ease-in-out;
    }

    .bn632-hover:focus {
        outline: none;
    }

    .bn632-hover.bn22 {
        background-image: linear-gradient(
                to right,
                #0ba360,
                #3cba92,
                #30dd8a,
                #2bb673
        );
        box-shadow: 0 4px 15px 0 rgba(23, 168, 108, 0.75);
    }
</style>
<body>
<div class="card">
    <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
        <i class="checkmark">✓</i>
    </div>
    <h1>Thanh toán thành công!</h1>
    <p>Đơn hàng của bạn sẽ được giao trong chốc lát!</p>
    <a href="/trang-chu"><button class="bn632-hover bn22">Quay lại trang chủ</button></a>
</div>
</body>
</html>
