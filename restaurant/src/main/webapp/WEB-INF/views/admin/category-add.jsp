<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url" value="/resources/admin"></c:url>

<%@include file="head.jsp" %>

<!-- Form Start -->
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Thêm danh mục</h6>

                <form action="<c:url value="/admin/quan-ly-danh-muc/insert" />">

                    <div class="mb-3">
                        <label for="id" class="form-label">Tên danh mục</label>
                        <input type="text" class="form-control" id="id" name="name"/>
                    </div>

                    <button type="submit" class="btn btn-primary">Thêm</button>

                </form>

            </div>
        </div>
    </div>
</div>

<%@ include file="foot.jsp" %>
