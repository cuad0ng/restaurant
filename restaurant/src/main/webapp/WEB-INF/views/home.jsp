<%@ page import="com.hcmute.model.ProductModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Nhà Hàng</title>
	<!-- Bootstrap css -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<!-- Font awesome cdn -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<!-- custom css -->
	<link rel="stylesheet" href="<c:url value="../resources/css/homepage.css"/>">
	<style>
		#header{
			background: url(<c:url value="../resources/images/header.jpg"/>) top/ cover no-repeat;
			width: 100%;
			padding-top: 12%
		}
	</style>
</head>
<body>
<!-- navbar -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark" style="background-color: #006a31;">
	<div class="container">
		<a href="<c:url value="/"/>" class="navbar-brand d-flex justify-content-between align-items-center order-lg-0">
			<img src="<c:url value="../resources/images/restaurant-logo.png"/>" alt="logo">
			<span class = "text-uppercase fw-lighter ms-2">The Restaurant</span>
		</a>

		<div class="order-lg-2 nav-btns">
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<button type="button" class="btn position-relative">
						<a href="${pageContext.request.contextPath}/cart/view/${sessionScope.account.customer_id}"><i class="fa fa-shopping-cart" style="color: #fff"></i></a>
						<span class = "position-absolute top-0 start-100 translate-middle badge bg-danger">${sessionScope.totalAmount}</span>
					</button>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${sessionScope.account == null}">
					<button type="button" class="btn position-relative">
						<a href="/login"><i class="fa fa-shopping-cart" style="color: #fff"></i></a>
						<span class = "position-absolute top-0 start-100 translate-middle badge bg-danger">0</span>
					</button>
				</c:when>
			</c:choose>


			<c:choose>
				<c:when test="${sessionScope.account != null}">
				<button type="button" class="btn position-relative">
					<i href="" class="fa-solid fa-user dropdown-toggle" data-toggle="dropdown" style="color: #fff"></i>
              	  <span class="caret"></span>
              	  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
              	    <li class="submenu-item"><a tabindex="-1" href="#" class="text-decoration-none">Tài khoản của tôi</a></li>
               	   <li class="submenu-item"><a tabindex="-1" href="#" class="text-decoration-none">Lịch sử mua hàng</a></li>
              	    <li class="divider"></li>
               	   <li class="submenu-item"><a tabindex="-1" href="<c:url value="/dang-xuat"/>" class="text-decoration-none">Đăng xuất</a></li>
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

		<button class = "navbar-toggler border-0" type = "button" data-bs-toggle = "collapse" data-bs-target = "#navMenu">
			<span class = "navbar-toggler-icon"></span>
		</button>

		<div class = "collapse navbar-collapse order-lg-1" id = "navMenu">
			<ul class = "navbar-nav mx-auto text-center">
				<li class = "nav-item px-2 py-2">
					<a class = "nav-link text-uppercase text-light" href = "/">Home</a>
				</li>
				<li class = "nav-item px-2 py-2">
					<a class = "nav-link text-uppercase text-light" href = "<c:url value="/menu"/>">Menu</a>
				</li>
				<li class = "nav-item px-2 py-2">
					<a class = "nav-link text-uppercase text-light" href = "<c:url value="/book-table"/>">Đặt bàn</a>
				</li>
				<li class = "nav-item px-2 py-2">
					<a class = "nav-link text-uppercase text-light" href = "#about">Về chúng tôi</a>
				</li>
				<li class = "nav-item px-2 py-2 border-0">
					<c:choose>
						<c:when test="${sessionScope.account != null}">
							<p class="nav-link text-uppercase text-light">
								<c:out value="${sessionScope.account.username}" /></p>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${sessionScope.account == null}">
							<a class = "nav-link text-uppercase text-light" href = "<c:url value="/login"/>">Đăng nhập/Đăng ký</a>
						</c:when>
					</c:choose>
				</li>
			</ul>
		</div>
	</div>
</nav>
<!-- end of navbar -->

<!-- header -->
<header id="header" class = "vh-100">
	<div class="container ">
		<div class="row">
			<div class="col-md-7 col-lg-6 ">
				<div class="detail-box">
					<h1>
						Giao Hàng Tận Nơi
					</h1>
					<p>
						Các món ăn sẽ được giao thẳng tới nhà bạn trong vòng 30p. Giữ nguyên hơi nóng như ăn tại nhà hàng!
					</p>
					<div class="btn-box">
						<a href="/menu" class="btn1">
							Xem Menu Ngay
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!-- end of header -->

<!-- new menu -->
<c:choose>
<c:when test="${requestScope.get('type') == 1}" >
<section id = "new" class = "py-5">
	<div class = "container">
		<div class = "title text-center py-5">
			<h2 class = "position-relative d-inline-block text-uppercase">Các món mới</h2>
		</div>

		<div class = "special-list row g-0">
			<%
				ArrayList<ProductModel> list = (ArrayList<ProductModel>) request.getAttribute("list8NewestProducts");
				for (ProductModel product:list) {%>
			<div class = "col-md-6 col-lg-4 col-xl-3 p-2">
				<div class = "special-img position-relative overflow-hidden">
					<img src = "<c:url value="<%=product.getImg()%>" />" class = "w-100" alt="picture">
				</div>
				<div class = "text-center">
					<p class = "text-capitalize mt-3 mb-1"><%=product.getName()%></p>
					<span class = "fw-bold d-block">
						<fmt:formatNumber type="number" maxFractionDigits="3" value="<%=product.getPrice()%>"/> đ
					</span>
					<a href = "${pageContext.request.contextPath}/view-product/<%=product.getId()%>" class = "btn btn-primary mt-3">Chi Tiết</a>
				</div>
			</div>
			<%}%>
		</div>
	</div>
</section>
</c:when>
</c:choose>
<!-- end of new menu -->

<!-- about us -->
<section id = "about" class = "py-5">
	<div class = "container">
		<div class = "row gy-lg-5 align-items-center">
			<div class = "col-lg-6 order-lg-1 text-center text-lg-start">
				<div class = "title pt-3 pb-5">
					<h2 class = "position-relative d-inline-block ms-4">Về Chúng tôi</h2>
				</div>
				<p class = "lead text-muted">Nhà hàng đã kinh doanh hơn 20 năm, có nhiều kinh nghiệm trong việc phục vụ các món ăn mang hương vị tuyệt hảo.</p>
				<p>Được thành lập vào năm 2001, The Restaurant bắt đầu mở rộng, phát triển hệ thống cửa hàng và nhượng quyền thương mại quốc tế. Lấy cảm hứng từ phong cách ẩm thực tinh tế của người Ý kết hợp cùng sự am hiểu về sở thích ẩm thực của người châu Á, chúng tôi đem lại cho khách hàng những hương vị thơm ngon, đến từ cả phong cách Đông và Tầy. Đảm bảo thoả mãn cho mọi khách hàng, kể cả những khách hàng khó tính nhất.</p>
			</div>
			<div class = "col-lg-6 order-lg-0">
				<img src = "<c:url value="../resources/images/about_us.jpg"/>" alt = "restaurant picture" class = "img-fluid">
			</div>
		</div>
	</div>
</section>
<!-- end of about us -->
<!-- footer section -->
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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