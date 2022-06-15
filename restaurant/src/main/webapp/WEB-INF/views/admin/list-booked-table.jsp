<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <link rel="stylesheet" href="<c:url value="/resources/css/booking-table.css"/>">
    <title>Quản lý bàn</title>
</head>
<body>
    <section id="table-management" class="container d-flex align-content-center flex-column pt-3 pb-3">

        <h2 class="text-center mt-2 mb-5">Quản lý bàn</h2>
        <div id="top-subnav">
            <div class="row">
                <div class="col-md-12">
                    <nav class="nav" id="subnav-link">
                       <a class="nav-link" href="<c:url value="/booking-table"/>">Chọn bàn</a>
                       <a class="nav-link active" href="<c:url value="/list-booked-table"/>">Danh sách đặt bàn</a>
                    </nav>
                </div>
            </div>
        </div>
        <div id="page-product">
            <div class="row">
                <div class="col-md-6">
                    <div class="search-form mt-3 mb-3">
                        <form action="<c:url value="/booking-table/search"/>" id="form-search-txt" method="GET">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Nhập số điện thoại khách hàng"
                                    aria-label="Recipient's username" aria-describedby="basic-addon2" name="phone">
                                <div class="input-group-append">
                                    <input type="submit" class="btn btn-primary" value="Tìm kiếm" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-bordered">
                    <thead>
                        <tr class="text-center">
                            <th scope="col">Số bàn</th>
                            <th scope="col">Ngày đến</th>
                            <th scope="col">Giờ đến</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listBooking}" var="e">
                            <div class="order-item">
                                <tr class="order-item-info">
                                    <td colspan="3">
                                        <span>Tên khách hàng:</span> ${e.key.getName()}
                                    </td>
                                    <td colspan="2">
                                        <span>Số điện thoại:</span> ${e.key.getPhone()}
                                    </td>
                                </tr>
                                <tr class="order-item-detail">
                                    <td class="col-2">
                                        <p>Bàn đã đặt: ${e.value.getTableId()}</p>
                                    </td>
                                    <td class="col-2 text-center">${e.value.getDate()}</td>
                                    <td class="col-2 text-center">${e.value.getArrivalTime()}</td>
                                    <td class="col-2 text-center text-capitalize">${e.value.getStatus()}</td>
                                    <td class="col-4 text-center">
                                        <a href="<c:url value="/booking-table/checked/${e.value.getId()}"/>" class="btn btn-outline-success">Nhận bàn</a>
                                        <a href="<c:url value="/booking-table/cancel/${e.value.getId()}"/>" class="btn btn-outline-danger">Hủy</a>
                                    </td>
                                </tr>
                            </div>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <!--list table-->
</body>
</html>