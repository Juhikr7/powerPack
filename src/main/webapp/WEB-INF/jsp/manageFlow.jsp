<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Flow </title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body onload="populateDropdown(1);" class="p-3">

<div class="container">

    <div class="row justify-content-center">
        <div class="col-lg-6">
            <h1 class="card-title">Manage  Flow</h1>
            <br/><a href="index.htm" class="btn btn-primary">Home</a>&nbsp;&nbsp;<a href="logout" class="btn btn-danger">Logout</a><br/><br/>
            <div id="dropdowns">
                <div class="form-group dropdown-container" id="div#1">
                    <label id="label#1"></label>
                    <select id="level#1" class="form-control mb-3" onchange="handleSelection(this)">
                        <option value="">Select an item</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<script>
    var data = ${mapLevelItemJson};
    var mapIdNextLvl = ${mapIdNextLvlJson};

    function populateDropdown(level) {
        const labelElement = document.getElementById("label#" + level);
        const dropdown = document.getElementById("level#" + level);
        dropdown.innerHTML = '<option value="">Select an item</option>';
        const filteredData = data[level];
        if (typeof filteredData !== 'undefined') {
            filteredData.forEach(item => {
                labelElement.innerHTML = item.uiLabel;
                const option = document.createElement('option');
                option.value = item.itemId;
                option.textContent = item.itemDescription;
                dropdown.appendChild(option);
            });
        }
        dropdown.innerHTML += '<option value="NaN">Add Item</option>';
    }

    function handleSelection(comp) {
        const level = comp.id.split('#')[1];
        const dropdown = document.getElementById("div#" + level);
        const divElement = document.getElementById("dynamicDiv#" + level) == null ? document.createElement('div') : document.getElementById("dynamicDiv#" + level);
        dropdown.appendChild(divElement);
        divElement.id = 'dynamicDiv#' + level;
        if (comp.value === "NaN") {
            divElement.innerHTML = '<label>Enter Code</label><input type="text" id="code#' + level + '" onkeypress="preventSpace(event);" class="form-control mb-2">' +
                '<label> Enter Item Desc</label> <input id="itemDesc#' + level + '" class="form-control mb-2"> ' +
                '<div id="nextLevelDiv#' + level + '"></div>';
            let str = '<label>Next Level</label><select id="nextLevel#' + level + '" onchange="nextLevelSelection(this);" class="form-control mb-2">' +
                ' <option value=""> Select next level</option>';
            const filteredData = data[level];
            if (typeof filteredData !== 'undefined') {
                filteredData.forEach(item => {
                    const nextLevel = item.nextLevel;
                    if (nextLevel in data) {
                        const nextLevelData = data[nextLevel][0].uiLabel != null ? data[nextLevel][0].uiLabel : '';
                        str += '<option value="' + nextLevel + '">' + nextLevelData + '</option>';
                    }
                });
            }
            str += '<option value="NaN">Other</option>' +
                '</select> <br>' +
                '<div id="uiLabelDyn" style="display: none">' +
                '<label>UI Label For Next Level</label><input type="text" id="uiLabelNew" class="form-control mb-2"/>' +
                '</div>' +
                '<div id="checkboxDiv">' +
                '<input type="checkbox" value="1" id="lastLevel#' + level + '"> <label>Last Flow</label><br>' +
                '</div>' +
                '<input type="submit" id="submit#' + level + '" value="Submit" onclick="saveItemData(this);" class="btn btn-primary">';
            divElement.innerHTML += str;
        } else {
            const divElement = document.getElementById("dynamicDiv#" + level);
            const currentLevelData = data[level];
            const nextLevel = mapIdNextLvl[comp.value];

            if (divElement != null) {
                currentLevelData.forEach(item => {
                    if (item.itemId == comp.value) {
                        divElement.innerHTML = '<div>' + item.itemDescription + '</div>' +
                            '<div id="div#' + nextLevel + '">' +
                            '<label id="label#' + nextLevel + '"></label>' +
                            '<select id="level#' + nextLevel + '" onchange="handleSelection(this)" class="form-control mb-2">' +
                            '</select>' +
                            '</div>';
                    }
                });
                populateDropdown(nextLevel);
            }
        }
    }

    function saveItemData(comp) {
        const level = comp.id.split('#')[1];
        const formData = new FormData();
        formData.append('code', document.getElementById("code#" + level).value);
        formData.append('itemDesc', document.getElementById("itemDesc#" + level).value);
        formData.append('level', level);
        formData.append('nextLevel', document.getElementById("nextLevel#" + level).value !== "NaN" ? document.getElementById("nextLevel#" + level).value : 0);
        formData.append('uiLabel', document.getElementById("uiLabelNew").value != null ? document.getElementById("uiLabelNew").value : "");
        formData.append('lastLevel', document.getElementById("lastLevel#" + level).checked);
        formData.append('${_csrf.parameterName}', '${_csrf.token}');
        const options = {
            method: 'POST',
            body: formData
        };

        fetch('addFlow.htm', options)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ajax call failed');
                }
                return response.json();
            })

            .then(data => {
                // console.log(data);

                location.reload();
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
    }

    function nextLevelSelection(comp) {
        if (comp.value === "NaN") {
            document.getElementById('uiLabelDyn').style.display = "block";
        } else {
            document.getElementById('uiLabelDyn').style.display = "none";
        }
    }

    function preventSpace(event) {
        if (event.key === ' ') {
            event.preventDefault();
        }
    }
</script>

</body>
</html>
