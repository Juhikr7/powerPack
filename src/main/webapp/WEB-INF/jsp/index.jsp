<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <sec:authentication property="principal" var="curUser" scope="request"/>
    <h4>User - ${curUser.name} </h4>
    <br>
    <div class="list-group">
        <sec:authorize access="hasRole('ROLE_BF_MANAGE_USER')">
            <a href="addUser.htm" class="list-group-item list-group-item-action">Add User</a>
            <a href="listUser.htm" class="list-group-item list-group-item-action">List User</a>
        </sec:authorize>
        <a href="showCustomerRegPage.htm" class="list-group-item list-group-item-action">Add Customer And Place Order</a>
        <a href="listOrder.htm" class="list-group-item list-group-item-action">List Order</a>

        <sec:authorize access="hasRole('ROLE_BF_MANAGE_USER')">
            <a href="manageFlow.htm" class="list-group-item list-group-item-action">Manage Flow</a>
        </sec:authorize>
    </div>
    <br>
    <a href="logout" class="btn btn-danger">Logout</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
