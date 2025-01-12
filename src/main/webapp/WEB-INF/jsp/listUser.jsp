<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        .card {
            margin-top: 20px;
        }
        .card-header {
            background-color: #007bff;
            color: #fff;
        }
        .card-header h5 {
            margin-bottom: 0;
        }
        .card-body {
            padding: 20px;
        }
        .table {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <br/><a href="index.htm" class="btn btn-primary">Home</a>&nbsp;&nbsp;<a href="logout" class="btn btn-danger">Logout</a><br/><br/>
    <div class="card">
        <div class="card-header">
            <h5 class="card-title mb-0">User List</h5> <br/>${param.msg}<h6></h6>
        </div>
        <div class="card-body">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th>Active</th>
                    <th>Edit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.username}</td>
                        <td>${user.role.label}</td>
                        <td>${user.active}</td>
                        <td><a href="javascript:editUser(${user.userId});" class="btn btn-sm btn-primary">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <form name="editForm" id="editForm" method="POST" action="showEditUser.htm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="userId" id="userId"/>
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function editUser(userId) {
        document.getElementById("userId").value = userId;
        document.getElementById("editForm").submit();
    }
</script>
</body>
</html>
