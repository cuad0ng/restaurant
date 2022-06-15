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
                        <a class="nav-link active" href="<c:url value="/booking-table"/>">Chọn bàn</a>
                        <a class="nav-link" href="<c:url value="/list-booked-table"/>">Danh sách đặt bàn</a>
                    </nav>
                </div>
            </div>
        </div>
        <div class="choose-table-container">
        <c:forEach items="${listTables}" var="t">
            <c:choose>
                <c:when test="${t.getStatus() == 'deactivate'}">
                    <div class="table-item deactivate"  data-id="${t.getId()}">
                    ${t.getId()}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-item" data-toggle="modal" data-target="#myModal" data-id="${t.getId()}">
                    ${t.getId()}
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </div>
    </section>
    <!-- Button trigger modal -->

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Thông tin khách hàng</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="form-info" action="<c:url value="/booking-table"/>" method="POST">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="fullname">Họ và tên</label>
                                    <input type="text" class="form-control" name="fullname">
                                </div>
                                <div class="form-group">
                                    <label for="phone">Số điện thoại</label>
                                    <input type="tel" class="form-control" name="phone">
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="date">Ngày</label>
                                    <input type="date" class="form-control" name="date">
                                </div>
                                <div class="form-group">
                                    <label for="time">Giờ</label>
                                    <input type="time" class="form-control" name="time">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <!--JS-->
    <script
    	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        const formInfo = document.querySelector("#form-info");
        formInfo.addEventListener('submit',function(e){
            e.preventDefault();
            var id = document.querySelector('.table-item.selected').dataset.id;
            var action = formInfo.getAttribute("action");
            var newAction = action +"/"+id;
            formInfo.setAttribute('action',newAction);
            formInfo.submit();
        })
        const listTables = document.querySelectorAll(".table-item");
        listTables.forEach((tableItem, index) => {
            tableItem.addEventListener('click', () => {
                if (!tableItem.classList.contains('deactivate')) {
                    var selectedTable = document.querySelector('.table-item.selected');
                    selectedTable && selectedTable.classList.remove('selected');
                    tableItem.classList.add('selected');
                }
            })
        })

    </script>
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
</body>
</html>