<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your cart</title>
    <!-- Bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Font awesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- custom css -->
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/../resources/css/homepage.css"/>">
    <style>
        #header {
            background: url(<c:url value="${pageContext.request.contextPath}/../resources/images/header.jpg"/>) top/ cover no-repeat;
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
            <img src="<c:url value="${pageContext.request.contextPath}/../resources/images/restaurant-logo.png"/>"
                 alt="logo">
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
                    <a class="nav-link text-uppercase text-light" href="<c:url value="/book-table"/>">Đặt bàn</a>
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
<section style="padding: 90px 0;">
    <div class="container">
        <table id="cart" class="table table-hover table-condensed">
            <thead>
            <tr>
                <th style="width:45%">Product</th>
                <th style="width:10%">Price</th>
                <th style="width:8%">Quantity</th>
                <th style="width:22%" class="text-center">Subtotal</th>
                <th style="width:15%"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cart" items="${cart}">
                <tr>
                    <td data-th="Product">
                        <div class="row">
                            <div class="col-sm-4 hidden-xs"><img style="width: 100%"
                                                                 src="<c:url value="${pageContext.request.contextPath}/${cart.img}"/>"
                                                                 alt="..."
                                                                 class="img-responsive"/>
                            </div>
                            <div class="col-sm-8">
                                <h4 class="nomargin">${cart.product_name}</h4>

                            </div>
                        </div>
                    </td>
                    <td data-th="Price">
                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${cart.product_price}"/> đ
                    </td>
                    <td data-th="Quantity">
                        <input class="form-control text-center" id="cart-quantity-${cart.productId}" type="number"
                               value="${cart.productAmount}" min="1" required="true">
                    </td>
                    <td data-th="Subtotal" class="text-center">
                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                          value="${cart.product_price * cart.productAmount}"/> đ
                    </td>
                    <td class="actions" data-th="">
                        <button data-id="${cart.productId}" class="btn btn-info btn-sm edit-cart"><i
                                class="fa fa-refresh"></i></button>

                        <a href="${pageContext.request.contextPath}/cart/delete/${cart.productId}"
                           style="text-decoration: none" class="btn btn-danger btn-sm"><i
                                class="fa fa-trash"></i></a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>

            <tr>
                <td><a href="${pageContext.request.contextPath}/menu" class="btn btn-warning"><i
                        class="fa fa-angle-left"></i> Continue Shopping</a></td>
                <td colspan="2" class="hidden-xs"></td>
                <td class="hidden-xs text-center"><strong>Total: <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                                   value="${sessionScope.totalPrice}"/>
                    đ</strong></td>

                <td>
                    <c:if test="${cart.size() > 0}">
                    <a href="${pageContext.request.contextPath}/checkout/${sessionScope.account.customer_id}"
                       class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a>
                    </c:if>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</section>
<%--<div class="shopping-cart">

    <div class="column-labels">
        <label class="product-image"></label>
        <label class="product-title"></label>
        <label class="product-price"></label>
        <label class="product-quantity"></label>
        <label class="product-removal"></label>
        <label class="product-removal"></label>
        <label class="product-line-price"></label>
    </div>
    <c:forEach var="cart" items="${cart}">
        <div class="product">
            <div class="product-image">
                <img src="<c:url value="${pageContext.request.contextPath}/${cart.img}"/> ">
            </div>

            <div class="product-title">${cart.product_name}</div>

            <div class="product-price">
                <fmt:formatNumber type="number" maxFractionDigits="3" value="${cart.product_price}"/> đ
            </div>
            <div class="product-quantity">
                <input id="cart-quantity-${cart.productId}" type="number" value="${cart.productAmount}" min="1">
            </div>
            <div class="product-removal">
                <button data-id="${cart.productId}" class="remove-product edit-cart">
                    Update
                </button>
            </div>
            <div class="product-removal">
                <a href="${pageContext.request.contextPath}/cart/delete/${cart.productId}" style="text-decoration: none"
                   class="remove-product">
                    Remove
                </a>
            </div>
            <div class="product-line-price">
                <fmt:formatNumber type="number" maxFractionDigits="3"
                                  value="${cart.product_price * cart.productAmount}"/> đ

            </div>
        </div>
    </c:forEach>

    <div class="totals">
        <div class="totals-item totals-item-total">
            <label>Grand Total</label>
            <div class="totals-value" id="cart-total">
                <fmt:formatNumber type="number" maxFractionDigits="3" value="${sessionScope.totalPrice}"/> đ

            </div>
        </div>
    </div>

    <a class="checkout" href="${pageContext.request.contextPath}/checkout/${sessionScope.account.customer_id}">Checkout</a>

</div>--%>
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
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
        integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
<script>
    $(".edit-cart").on("click", function () {
        var id = $(this).data("id");
        var quantity = $("#cart-quantity-" + id).val()

        if (!quantity) {
            window.location = "${pageContext.request.contextPath}/cart/view/" +
            ${sessionScope.account.customer_id};
        } else {
            window.location = "${pageContext.request.contextPath}/cart/edit/" + id + "/" + quantity;
        }
    });
</script>

</body>

</html>