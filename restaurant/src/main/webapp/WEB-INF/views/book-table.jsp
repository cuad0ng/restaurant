<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhà Hàng</title>
    <!-- Bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Font awesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- custom css -->
    <link rel="stylesheet" href="<c:url value="../resources/css/homepage.css"/>">
    <style>
        #header {
            background: url(<c:url value="../resources/images/header.jpg"/>) top/ cover no-repeat;
            width: 100%;
            padding-top: 12%
        }
    </style>
    <link rel="stylesheet" href="<c:url value="../resources/css/reservation.css"/> ">
</head>
<body>
<!-- navbar -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark" style="background-color: #006a31;">
    <div class="container">
        <a href="<c:url value="/"/>" class="navbar-brand d-flex justify-content-between align-items-center order-lg-0">
            <img src="<c:url value="../resources/images/restaurant-logo.png"/>" alt="logo">
            <span class="text-uppercase fw-lighter ms-2">The Restaurant</span>
        </a>

        <div class="order-lg-2 nav-btns">
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <button type="button" class="btn position-relative">
                        <a href="${pageContext.request.contextPath}/cart/view/${sessionScope.account.customer_id}"><i
                                class="fa fa-shopping-cart" style="color: #fff"></i></a>
                        <span class="position-absolute top-0 start-100 translate-middle badge bg-danger">${sessionScope.totalAmount}</span>
                    </button>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${sessionScope.account == null}">
                    <button type="button" class="btn position-relative">
                        <a href="/login"><i class="fa fa-shopping-cart" style="color: #fff"></i></a>
                        <span class="position-absolute top-0 start-100 translate-middle badge bg-danger">0</span>
                    </button>
                </c:when>
            </c:choose>


            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <button type="button" class="btn position-relative">
                        <i href="" class="fa-solid fa-user dropdown-toggle" data-toggle="dropdown"
                           style="color: #fff"></i>
                        <span class="caret"></span>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                            <li class="submenu-item"><a tabindex="-1" href="#" class="text-decoration-none">Tài khoản
                                của tôi</a></li>
                            <li class="submenu-item"><a tabindex="-1" href="#" class="text-decoration-none">Lịch sử mua
                                hàng</a></li>
                            <li class="divider"></li>
                            <li class="submenu-item"><a tabindex="-1" href="<c:url value="/dang-xuat"/>"
                                                        class="text-decoration-none">Đăng xuất</a></li>
                        </ul>
                    </button>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${sessionScope.account == null}">
                    <button type="button" class="btn position-relative">
                        <a href="/login"> <i class="fa-solid fa-user" style="color: #fff"></i></a>
                    </button>
                </c:when>
            </c:choose>
        </div>

        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse order-lg-1" id="navMenu">
            <ul class="navbar-nav mx-auto text-center">
                <li class="nav-item px-2 py-2">
                    <a class="nav-link text-uppercase text-light" href="/">Home</a>
                </li>
                <li class="nav-item px-2 py-2">
                    <a class="nav-link text-uppercase text-light" href="<c:url value="/menu"/>">Menu</a>
                </li>
                <li class="nav-item px-2 py-2">
                    <a class="nav-link text-uppercase text-light" href="<c:url value="/dat-cho"/>">Đặt bàn</a>
                </li>
                <li class="nav-item px-2 py-2">
                    <a class="nav-link text-uppercase text-light" href="#about">Về chúng tôi</a>
                </li>
                <li class="nav-item px-2 py-2 border-0">
                    <c:choose>
                        <c:when test="${sessionScope.account != null}">
                            <p class="nav-link text-uppercase text-light">
                                <c:out value="${sessionScope.account.username}"/></p>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${sessionScope.account == null}">
                            <a class="nav-link text-uppercase text-light" href="<c:url value="/login"/>">Đăng nhập/Đăng
                                ký</a>
                        </c:when>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- end of navbar -->
<section class="book_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h2>
                Đặt bàn trước
            </h2>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form_container">
                    <form action="<c:url value="/book-table/book/${sessionScope.account.customer_id}" />" method="get">
                        <div>
                            <label for="name">Tên</label>
                            <input required id="name" name="name" type="text" class="form-control"/>
                        </div>
                        <div>
                            <label for="phone">SĐT</label>
                            <input required id="phone" name="phone" type="tel" class="form-control"/>
                        </div>
                        <div>
                            <select required name="table_id" class="form-control nice-select wide">
                                <option value="" disabled selected>
                                    Bàn
                                </option>
                                <c:forEach var="table" items="${tables}">
                                <option value="${table.id}">
                                    Bàn số ${table.id}
                                </option>
                                </c:forEach>

                            </select>
                        </div>
                        <div>
                            <label for="date">Ngày</label>
                            <input required id="date" name="date" type="date" class="form-control"/>
                        </div>
                        <div>
                            <label for="time">Giờ</label>
                            <input required id="time" name="time" type="time" class="form-control" />
                        </div>

                        <div class="btn_box">
                            <button type="submit">
                                Book Now
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
                <div class="map_container ">
                    <div id="googleMap">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.484300945954!2d106.76973361442862!3d10.850721392271105!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752763f23816ab%3A0x282f711441b6916f!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBTxrAgcGjhuqFtIEvhu7kgdGh14bqtdCBUaMOgbmggcGjhu5EgSOG7kyBDaMOtIE1pbmg!5e0!3m2!1svi!2s!4v1653454516034!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer class="footer_section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 footer-col">
                <div class="footer_contact">
                    <h4>
                        Liên hệ
                    </h4>
                    <div class="contact_link_box">
                        <a href="">
                            <i class="fa fa-map-marker" aria-hidden="true"></i>
                            <span>
                  01 Võ Văn Ngân
                </span>
                        </a>
                        <a href="">
                            <i class="fa fa-phone" aria-hidden="true"></i>
                            <span>
                  SĐT: +01 1234567890
                </span>
                        </a>
                        <a href="">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                            <span>
                  mail@gmail.com
                </span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 footer-col">
                <div class="footer_detail">
                    <h4>
                        The Restaurant
                    </h4>
                    <p>
                        Nhà Hàng Số 1 Miền Nam
                    </p>
                </div>
            </div>
            <div class="col-md-4 footer-col">
                <h4>
                    Giờ mở cửa
                </h4>
                <p>
                    Mỗi ngày từ:
                </p>
                <p>
                    00:01 AM - 11:59 PM
                </p>
            </div>
        </div>
    </div>
</footer>
<!-- end of footer section -->
</body>
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<!-- JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
        crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"
        type="text/javascript"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</html>