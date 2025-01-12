<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script type="text/javascript">
        var data = ${mapLevelItemJson};
        var mapIdNextLvl = ${mapIdNextLvlJson};

        function populateDropdown(level) {
            const labelElement = document.getElementById("label#" + level);
            const dropdown = document.getElementById("level#" + level);
            dropdown.innerHTML = '<option value="">Select an item</option>';
            const filteredData = data[level];

            filteredData.forEach(item => {
                labelElement.innerHTML = item.uiLabel;
                const option = document.createElement('option');
                option.value = item.itemId;
                option.textContent = item.itemDescription;
                dropdown.appendChild(option);
            });
        }

        function handleSelection(comp) {
            const nextLevel = mapIdNextLvl[comp.value];

            if (nextLevel == 0) {
                document.getElementById('submit').style.display = "block";
            } else {
                document.getElementById('submit').style.display = "none";
                const dropdownsContainer = document.getElementById('dropdowns');

                const childArray = Array.from(dropdownsContainer.children);
                for (const child of childArray) {
                    const childIdParts = child.id.split('#');
                    const compIdParts = comp.id.split('#');
                    if (parseInt(childIdParts[1]) > parseInt(compIdParts[1])) {
                        dropdownsContainer.removeChild(child);
                    }
                }

                if(comp.value != ''){
                    const newDropdownContainer = document.createElement('div');
                    newDropdownContainer.id = 'div#' + nextLevel;
                    newDropdownContainer.className = 'form-group';
                    const newLabel = document.createElement('label');
                    newLabel.id = "label#" + nextLevel;
                    newLabel.setAttribute('for', "level#" + nextLevel);
                    const newDropdown = document.createElement('select');
                    newDropdown.id = "level#" + nextLevel;
                    newDropdown.className = "form-control w-50";
                    newDropdown.onchange = () => handleSelection(newDropdown);

                    newDropdownContainer.appendChild(newLabel);
                    newDropdownContainer.appendChild(newDropdown);
                    dropdownsContainer.appendChild(newDropdownContainer);
                    populateDropdown(nextLevel);
                }
            }
        }

        function submitForm() {
            var selectedIdsStr = "";
            for (let i = 0; i <= Object.keys(mapIdNextLvl).length; i++) {
                const element = document.getElementById("level#" + i);
                if (element != null) {
                    if (element.value == '') {
                        alert("Please Select all Dropdown Value");
                        return;
                    }
                    selectedIdsStr += element.value + '-';
                }
            }
            if (selectedIdsStr != '') {
                document.getElementById('selectedIdsHidden').value = selectedIdsStr.slice(0, -1);
                document.getElementById('submitForm').submit();
            }
            // console.log(selectedIdsStr);
        }
    </script>
    <style>
        .dropdown-container {
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .card {
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body onload="populateDropdown(1);" class="container mt-4">
<sec:authentication property="principal" var="curUser" scope="request" />
<div class="card">
    <div class="card-body">
        <br/><a href="index.htm" class="btn btn-primary">Home</a>&nbsp;&nbsp;<a href="logout" class="btn btn-danger">Logout</a><br/><br/>
        <div id="dropdowns">
            <div class="form-group dropdown-container" id="div#1">
                <label id="label#1"></label>
                <select id="level#1" class="form-control w-50" onchange="handleSelection(this)">
                    <option value="">Select an item</option>
                </select>
            </div>
        </div>
        <div class="mt-3">
            <button id="submit" class="btn btn-primary" onclick="submitForm();" style="display: none">Submit</button>
        </div>
        <form:form id="submitForm" action="saveOrderDetails.htm" method="post" modelAttribute="customer">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <form:hidden path="customerId" />
            <form:hidden path="customerName" id="customerName"/>
            <form:hidden path="mobile" />
            <form:hidden path="emailId" />
            <form:hidden path="age" />
            <form:hidden path="gender" />
            <input type="hidden" value="" id="selectedIdsHidden" name="selectedIds" />
        </form:form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
