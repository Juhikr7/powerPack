<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        .card {
            margin-top: 20px;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-cancel {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title">Add New User</h1>
            <form:form name="addUser" modelAttribute="user" method="POST" cssClass="needs-validation" novalidate="novalidate">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <div class="form-group">
                    <label for="name">Name:</label>
                    <form:input path="name" id="name" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        Please enter a name (minimum 4 characters).
                    </div>
                </div>

                <div class="form-group">
                    <label for="username">Username:</label>
                    <form:input path="username" id="username" class="form-control" required="required" minlength="4"/>
                    <div class="invalid-feedback">
                        Please enter a username (minimum 4 characters).
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <form:input path="email" id="email" class="form-control" required="required" minlength="4"/>
                    <div class="invalid-feedback">
                        Please enter a email (minimum 4 characters).
                    </div>
                </div>

                <div class="form-group">
                    <label for="role">Role:</label>
                    <form:select path="role" id="role" class="form-control" items="${roleList}" itemLabel="label" itemValue="id" onchange="showSpecialities();" required="required">
                        <option value="">Select Role</option>
                    </form:select>
                    <div class="invalid-feedback">
                        Please select a role.
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="button" class="btn btn-secondary btn-cancel" onclick="window.location.href = 'listUser.htm?msg=Cancelled';">Cancel</button>
            </form:form>
        </div>
    </div>

    <br/><br/>
    ${msg}${param.msg}
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
