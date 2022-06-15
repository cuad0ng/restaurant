<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hcmute.model.ProductModel" %>
<%@ page import="com.hcmute.model.CommentModel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcmute.model.AccountModel" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hao_n
  Date: 5/19/2022
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <!-- Bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Font awesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- custom css -->
    <link rel="stylesheet" href="<c:url value="../resources/css/homepage.css"/>">
    <style>
        .show{
            display: flex;
            flex-direction: column;
            height: max-content;
        }
        .show-product{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
        .content-product{
            display: flex;
            width: 20%;
            flex-direction: column;
            justify-content: space-between;
        }
        .show-comment{
            height: 50%;
            font-size: smaller;
            font-style: italic;
            background-color: #fffcf8;
            padding: 1rem;
            justify-content: space-between;
            display: flex;
            flex-direction: column;
            overflow-y: scroll;
            width: 100%;
        }

        .image-product > img{
            border-radius: 9px;
        }

        #input_comment{
            border-radius: 99px;
            padding: 2px;
        }
        #input_comment:focus{
            outline: 0;
        }

        #send_comment{
            border-radius: 9px;
            background-color: #8effff;
        }
        form{
            margin-top: 1rem;
            position: fixed;
            background-color: #fffcf8;
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
                    <a class = "nav-link text-uppercase text-light" href = "/menu">Menu</a>
                </li>
                <li class = "nav-item px-2 py-2">
                    <a class = "nav-link text-uppercase text-light" href = "<c:url value="/book-table"/>">Đặt bàn</a>
                </li>
                <li class = "nav-item px-2 py-2">
                    <a class = "nav-link text-uppercase text-light" href = "/trang-chu#about">Về chúng tôi</a>
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
<!-- new menu -->
<c:choose>
    <c:when test="${requestScope.get('type') == 1}" >
        <section id = "new" class = "py-5">
            <div class = "container">
                <div class = "title text-center py-5">
                    <h2 class = "position-relative d-inline-block text-uppercase">Các món ăn mới nhất của nhà hàng</h2>
                </div>

                <div class = "special-list row g-0">
                    <%
                        ArrayList<ProductModel> list = (ArrayList<ProductModel>) request.getAttribute("listProducts");
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
                            <a href = "/view-product/<%=product.getId()%>" class = "btn btn-primary mt-3">Chi Tiết</a>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
            </div>
        </section>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${requestScope.get('type') == 2}">
        <section id = "new" class = "py-5">
            <div class = "container">
                <%
                    ProductModel product = (ProductModel) request.getAttribute("product");
                %>

                <div class = "title text-center py-5">
                    <h2 class = "position-relative d-inline-block text-uppercase"><%=product.getName().toUpperCase()%></h2>
                </div>

                <div class = "show">

                    <div class = "show-product">
                        <div class = "image-product">
                            <img src = "<%=product.getImg()%>" class = "w-100" alt="picture">
                        </div>
                        <div class = "content-product">
                            <div>
                                <p class = "text-capitalize mt-3 mb-1"><%=product.getName()%></p>
                                <span class = "fw-bold d-block"><%=product.getPrice()%> đ</span>
                                <span class = "text-capitalize mt-3 mb-1"><%=product.getDescription()%></span>
                            </div>
                            <div>
                                <a href = "${pageContext.request.contextPath}/cart/add/<%=product.getId()%>" class = "btn btn-primary mt-3">Thêm Vào Giỏ Hàng</a>
                            </div>

                            <div class = "show-comment">



                                <div class = "comment">
                                    <%
                                        List<CommentModel> comments = (List<CommentModel>) request.getAttribute("comments");
                                        for(CommentModel comment: comments){%>
                                    <p class = "text-capitalize mt-3 mb-1"><%=comment.getComment()%></p>
                                    <%}%>
                                </div>


                                <div>
                                    <% AccountModel account = (AccountModel) session.getAttribute("account"); %>
                                    <c:choose>
                                        <c:when test="${sessionScope.account == null}">
                                            <p>Vui lòng đăng nhập để viết bình luận</p>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${sessionScope.account != null}">
                                            <form action="/insert-comment/<%=account.getId()%>/<%=product.getId()%>" method="POST">
                                                <input type="text" id="input_comment" name="content" placeholder="your comment" />
                                                <button id="send_comment" type="submit">Gửi</button>
                                            </form>
                                        </c:when>
                                    </c:choose>

                                </div>

                            </div>

                        </div>
                    </div>


                </div>



            </div>
        </section>
    </c:when>
</c:choose>

</body>
</html>
