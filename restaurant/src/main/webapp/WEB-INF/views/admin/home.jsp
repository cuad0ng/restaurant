<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="url" value="/resources/admin"></c:url>

<%@include file="head.jsp" %>

<!-- Sale & Revenue Start -->
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-6 col-xl-3">
            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i class="fa fa-chart-line fa-3x text-primary"></i>
                <div class="ms-3">
                    <p class="mb-2">Doanh thu ngày</p>
                    <h6 class="mb-0">
                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                          value="${total_sale_today}"/> đ

                    </h6>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-xl-3">
            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i class="fa fa-chart-bar fa-3x text-primary"></i>
                <div class="ms-3">
                    <p class="mb-2">Tổng doanh thu</p>
                    <h6 class="mb-0">
                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                          value="${total_sale_all}"/> đ

                    </h6>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Sale & Revenue End -->


<!-- Sales Chart Start -->
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light text-center rounded p-4">
                <div class="d-flex align-items-center justify-content-between mb-4">
                    <h6 class="mb-0">Thống kê doanh thu theo sản phẩm</h6>
                    <a href="${pageContext.request.contextPath}/admin/quan-ly-tai-chinh/doanh-thu-san-pham">Hiển
                        thị tất cả</a>
                </div>
                <canvas id="myBarChart"></canvas>
            </div>
        </div>

    </div>
</div>
<!-- Sales Chart End -->


<!-- Recent Sales Start -->
<div class="container-fluid pt-4 px-4">
    <div class="bg-light text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
            <h6 class="mb-0">Đơn hàng gần đây</h6>
            <a href="<c:url value="/admin/quan-ly-don-hang/"/>">Hiển thị tất cả</a>
        </div>
        <div class="table-responsive">
            <table class="table text-start align-middle table-bordered table-hover mb-0">
                <thead>
                <tr class="text-dark">
                    <th scope="col">Mã đơn hàng</th>
                    <th scope="col">Mã khách hàng</th>
                    <th scope="col">Mã nhân viên</th>
                    <th scope="col">Mã giỏ hàng</th>
                    <th scope="col">Tổng cộng</th>
                    <th scope="col">Ngày tạo</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="invoices" items="${invoices_part}">
                    <tr>
                        <td>${invoices.id}</td>
                        <td>${invoices.cusId}</td>
                        <td>${invoices.empId}</td>
                        <td>${invoices.cartId}</td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                              value="${invoices.totalCost}"/> đ

                        </td>
                        <td>${invoices.createDate}</td>
                        <td>
                            <a class="btn btn-sm btn-primary"
                               href="${pageContext.request.contextPath}/admin/quan-ly-don-hang/view?id=${invoices.id}">Detail</a>
                            <a class="btn btn-sm btn-danger"
                               href="${pageContext.request.contextPath}/admin/quan-ly-don-hang/delete/${invoices.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    let productLabels = [], productInfo = [];
    <c:forEach var="list" items="${statistic_product}">
    productLabels.push('${list.name}')
    productInfo.push(Number('${list.total_cost}'))
    </c:forEach>
    window.onload = function () {
        statsProduct("myBarChart", productLabels, productInfo)
    }

</script>
<!-- Recent Sales End -->


<!-- Widgets Start -->

<!-- Widgets End -->

<%@ include file="foot.jsp" %>