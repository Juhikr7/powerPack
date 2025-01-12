<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script >
       function disableFeild(){
           document.getElementById('username').readOnly = true ;
       }
   </script>
</head>
<body onload="disableFeild();">
    <div class="container mt-5" >
        <h1>Edit User</h1>
        <form:form name="editUser" modelAttribute="user" method="POST" action="editUser.htm" class="needs-validation" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <form:hidden path="userId" />

            <div class="form-group">
                <label for="name">Name:</label>
                <form:input path="name" id="name" class="form-control" />
            </div>

            <div class="form-group">
                <label for="username">Username:</label>
                <form:input path="username" id="username" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <form:input path="email" id="email" class="form-control" />
            </div>

            <div class="form-group">
                <label for="role">Role:</label>
                <form:select path="role" id="role" class="form-control">
                    <form:options items="${roleList}" itemValue="id" itemLabel="label"/>
                </form:select>
            </div>

            <div class="form-group">
                <label>Status:</label><br/>
                <form:radiobutton path="active" id="active" value="true" label="Active" />
                <form:radiobutton path="active" id="deactivate" value="false" label="Deactivate" />
            </div>

            <button type="submit" class="btn btn-primary mr-2">Submit</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href = 'listUser.htm?msg=Cancelled';">Cancel</button>
        </form:form>

        <br/>
        <c:if test="${not empty msg}">
            <div class="alert alert-info">${msg}</div>
        </c:if>
    </div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
