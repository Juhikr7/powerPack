<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script type="text/javascript">
        function getDefaultList(comp) {
            if (comp.value == '') {
                window.location.href = "listOrder.htm";
            }
        }
    </script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .legend-custom {
            width: auto;
            padding: 0 10px;
            font-size: 1.2rem;
            font-weight: bold;
        }
        .form-inline-custom {
            display: flex;
            flex-wrap: nowrap;
        }
        .form-control {
            margin-right: 10px;
        }
        .table-custom th, .table-custom td {
            vertical-align: middle;
        }
    </style>
</head>
<body onload="document.getElementById('searchString').focus();" class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm p-3 mb-5 bg-white rounded">
    <a class="navbar-brand" href="index.htm">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="alert alert-info" role="alert">
    ${msg}
</div>

<fieldset class="border p-4 bg-white rounded shadow-sm">
    <legend class="legend-custom">Search Customer</legend>
    <form id="searchCustomer" name="searchCustomer" method="GET" class="form-inline form-inline-custom mb-3">
        <input type="text" class="form-control flex-grow-1" name="searchString" id="searchString" value="${searchString}" onkeyup="getDefaultList(this);" placeholder="Search Customer"/>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover table-custom">
            <thead class="thead-dark">
            <tr>
                <th>Customer Name</th>
                <th>Email Id</th>
                <th>Order Id</th>
                <th>Created By</th>
                <th>Order Date</th>
                <th>Order Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customerList}" var="cust">
                <tr>
                    <td>${cust.customerName}</td>
                    <td>${cust.emailId}</td>
                    <td>${cust.orderId}</td>
                    <td>${cust.createdBy.label}</td>
                    <td>${cust.orderDate}</td>
                    <td>${cust.orderTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
