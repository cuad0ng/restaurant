<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./assets/login-register.css">
    <title>Đăng nhập và đăng ký</title>
</head>
<body>
    <div class="container-fluid">
        <div class="page-content">
            <div class="row">
                <div id="form-content">
                    <!--Dang nhap-->
                    <div class="wrapper" id="login">
                        <h5 class="text-center pb-2">Đăng nhập</h5>
                        <c:if test="${msg != null}">
                        	<h6 class="alert alert-danger">${msg}</h6>
                        </c:if>
                        <form action="<c:url value="/login"/>" method = "POST">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" id="inputUsername"
                                        placeholder="Tên tài khoản" name="username" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="password" class="form-control" id="inputPassword"
                                        placeholder="Mật khẩu" name="password" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="submit" class="btn " value="Đăng nhập">
                                </div>
                            </div>
                        </form>
                        <div class="sub-info">
                            <p>Chưa có tài khoản?<a class="nav-link" href="#" onclick="createShop(2)"> Đăng ký</a></p>
                        </div>
                    </div>
                    <!--End Dang nhap-->
                    <!--Dang ky-->
                    <div class="wrapper" id="register">
                        <h5 class="text-center pb-2">Đăng ký</h5>
                        <form action="<c:url value="/register"/>" method = "POST" id="form-register">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control"
                                        placeholder="Tên tài khoản" name="username">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="password" class="form-control"
                                        placeholder="Mật khẩu" name="password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <input type="password"  name="confirm" class="form-control" id="confirm" placeholder="Nhập lại mật khẩu">
                                </div>
                            </div>
                             <div class="form-group row">
                                 <div class="col-sm-12">
                                     <input type="submit" class="btn " value="Đăng ký">
                                 </div>
                             </div>

                        </form>
                        <div class="sub-info">
                            <p>Về trang<a class="nav-link" href="#" onclick="createShop(1)"> Đăng nhập</a></p>
                        </div>
                    </div>

                    <!--End Dang ky-->
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    function createShop(type) {
        var login = document.getElementById("login");
        var register = document.getElementById("register");
        if (type === 1) {
            register.style.display = "none";
            login.style.display = "block";
        } else {
            login.style.display = "none";
            register.style.display = "block";
        }
    }
</script>
</html>