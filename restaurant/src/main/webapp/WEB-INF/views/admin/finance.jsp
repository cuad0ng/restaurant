<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url" value="/resources/admin"></c:url>

<%@include file="head.jsp" %>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light text-center rounded p-4">
                <div class="d-flex align-items-center justify-content-between mb-4">
                    <h6 class="mb-0">Thống kê doanh thu theo sản phẩm</h6>
                </div>
                <canvas id="stats-product-options"></canvas>
            </div>
        </div>
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Tuỳ chọn</h6>
                <form action="${pageContext.request.contextPath}/admin/quan-ly-tai-chinh/doanh-thu-san-pham">
                    <div class="mb-3">
                        <label for="keyword" class="form-label">Từ khoá</label>
                        <input required type="text" class="form-control" id="keyword" name="keyword" />
                    </div>
                    <div class="mb-3">
                        <label for="from_date" class="form-label">Ngày bắt đầu</label>
                        <input required type="date" class="form-control" id="from_date" name="from_date" />
                    </div>
                    <div class="mb-3">
                        <label for="to_date" class="form-label">Ngày kết thúc</label>
                        <input required type="date" class="form-control" id="to_date" name="to_date" />
                    </div>
                    <button type="submit" class="btn btn-primary">Thống kê</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid pt-4 px-4">
    <div class="bg-light text-center rounded p-4">
        <div class="table-responsive">
            <table id="myTable" class="table text-start align-middle table-bordered table-hover mb-0">
                <thead>
                <tr class="text-dark">

                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Tổng doanh thu</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="stats" items="${statistic_product_options}">
                    <tr>
                        <td>${stats.name}</td>
                        <td>${stats.total_cost}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    let statsProductLabels = [], statsProductInfo = [];
    <c:forEach var="stats" items="${statistic_product_options}">
    statsProductLabels.push('${stats.name}')
    statsProductInfo.push(Number('${stats.total_cost}'))
    </c:forEach>
    window.onload = function () {
        statsProductOptions("stats-product-options", statsProductLabels, statsProductInfo)
    }
</script>

<%@ include file="foot.jsp" %>

