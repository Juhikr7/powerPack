<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer and Place Order</title>
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
        <div class="card-header">
            <h5 class="card-title mb-0">Add Customer and Place Order</h5>
        </div>
        <div class="card-body">
            <form:form name="addCustomer" modelAttribute="customer" method="POST" action="/addItems.htm" cssClass="needs-validation" novalidate="novalidate">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <div class="form-group">
                    <label for="customerName">Name:</label>
                    <form:input path="customerName" id="customerName" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        Please enter a name.
                    </div>
                </div>

                <div class="form-group">
                    <label for="mobile">Mobile:</label>
                    <form:input path="mobile" id="mobile" class="form-control" required="required" pattern="\d{10}"/>
                    <div class="invalid-feedback">
                        Please enter a mobile number.
                    </div>
                </div>

                <div class="form-group">
                    <label for="emailId">Email Id:</label>
                    <form:input path="emailId" id="emailId" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="age">Age:</label>
                    <form:input path="age" id="age" class="form-control" type="number" required="required" />
                    <div class="invalid-feedback">
                        Please enter an age.
                    </div>
                </div>

                <div class="form-group">
                    <label>Gender:</label><br/>
                    <form:radiobuttons path="gender" items="${genderList}" itemLabel="label" itemValue="id" cssClass="form-check-inline" cssStyle="margin-left: 20px;"/>
                </div>

                <button type="submit" class="btn btn-primary">Place Order</button>
                <button type="button" class="btn btn-secondary btn-cancel" onclick="window.location.href = 'index.htm';">Cancel</button>
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
