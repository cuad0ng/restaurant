<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.98.0">
  <title>Checkout</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }

    .b-example-divider {
      height: 3rem;
      background-color: rgba(0, 0, 0, .1);
      border: solid rgba(0, 0, 0, .15);
      border-width: 1px 0;
      box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
    }

    .b-example-vr {
      flex-shrink: 0;
      width: 1.5rem;
      height: 100vh;
    }

    .bi {
      vertical-align: -.125em;
      fill: currentColor;
    }

    .nav-scroller {
      position: relative;
      z-index: 2;
      height: 2.75rem;
      overflow-y: hidden;
    }

    .nav-scroller .nav {
      display: flex;
      flex-wrap: nowrap;
      padding-bottom: 1rem;
      margin-top: -1px;
      overflow-x: auto;
      text-align: center;
      white-space: nowrap;
      -webkit-overflow-scrolling: touch;
    }
  </style>
</head>
<body class="bg-light">

<div class="container">
  <main>
    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-success">Giỏ Hàng</span>
          <span class="badge bg-success rounded-pill">${sessionScope.totalAmount}</span>
        </h4>
        <ul class="list-group mb-3">
            <c:forEach var="cart" items="${cart}">
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">${cart.product_name}</h6>
              <small class="text-muted">Số Lượng: ${cart.productAmount}</small>
            </div>
            <span class="text-muted">
              <fmt:formatNumber type="number" maxFractionDigits="3" value="${cart.product_price * cart.productAmount}"/> đ
            </span>
          </li>
            </c:forEach>
          <li class="list-group-item d-flex justify-content-between">
            <span>Tổng Cộng</span>
            <strong>
              <fmt:formatNumber type="number" maxFractionDigits="3" value="${sessionScope.totalPrice}"/> đ
            </strong>
          </li>
        </ul>

      </div>
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Thông Tin Thanh Toán</h4>
        <form class="needs-validation" novalidate>
          <div class="row g-3">
            <div class="col-12">
              <label for="name" class="form-label">Họ Tên</label>
              <input type="text" class="form-control" id="name" placeholder="Nguyen Van A" value="" required="true">
              <div class="invalid-feedback">
                Vui lòng điền họ tên.
              </div>
            </div>

            <div class="col-12">
              <label for="address" class="form-label">Địa Chỉ</label>
              <input type="text" class="form-control" id="address" placeholder="01 Võ Văn Ngân" required="true">
              <div class="invalid-feedback">
                Vui lòng điền địa chỉ hợp lệ.
              </div>
            </div>

            <div class="col-12">
              <label for="phone" class="form-label">Số Điện Thoại</label>
              <input type="text" class="form-control" id="phone" placeholder="0123456789" required="true">
              <div class="invalid-feedback">
                Vui lòng điền số điện thoại hợp lệ.
              </div>
            </div>


          </div>

          <hr class="my-4">

          <a class="w-100 btn btn-success btn-lg" href="${pageContext.request.contextPath}/checkout-confirmed/${sessionScope.account.customer_id}">Xác Nhận Thanh Toán</a>
        </form>
      </div>
    </div>
  </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script>
  (() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
</script>
</body>
</html>
