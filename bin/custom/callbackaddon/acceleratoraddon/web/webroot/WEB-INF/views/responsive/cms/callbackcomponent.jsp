<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<head>
<style>
    body {font-family: Arial, Helvetica, sans-serif;}
    * {box-sizing: border-box;}

    .open-button {
        background-color: #555;
        color: white;
        padding: 16px 20px;
        border: none;
        cursor: pointer;
        opacity: 0.8;
        position: fixed;
        bottom: 23px;
        right: 28px;
        width: 280px;
        z-index: 9;
    }

    .form-popup {
        display: none;
        position: fixed;
        bottom: 0;
        right: 15px;
        border: 3px solid #f1f1f1;
        z-index: 9;
    }

    .form-container {
        max-width: 300px;
        padding: 10px;
        background-color: white;
    }

    .form-container input[type=text] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        border: none;
        background: #f1f1f1;
    }

    .form-container input[type=text]:focus {
        background-color: #ddd;

        outline: none;
    }

    .form-container .btn {
        background-color: #4CAF50;
        color: white;
        padding: 16px 20px;
        border: none;
        cursor: pointer;
        width: 100%;
        margin-bottom:10px;
        opacity: 0.8;
    }

    .form-container .cancel {
        background-color: red;
    }

    /* Add some hover effects to buttons */
    .form-container .btn:hover, .open-button:hover {
        opacity: 1;
    }
</style>
</head>
<body>
<button class="open-button" onclick="openForm()" id="openButton">${openB}</button>
<div class="form-popup" id="myForm">
    <form action="javascript:submitForm()" class="form-container">

        <span id="headText"><h1>${headText} </h1></span>
        <label for="inputName" id="textForInputName"><b>${customerName}</b><br></label>
        <input type="text" placeholder="${inputName}" id="inputName" required>
        <label for="inputPhone"  id="textForInputPhone"><b>${customerPhone}</b><br></label>
        <input type="text" placeholder="+375 29 1234567" id="inputPhone" required>
        <span id="confirmationText" style="display: none"><h2>${confirmationText}</h2><br></span>
        <label for="inputComment" id="textForInputComment"><b>${comment}</b></label>
        <input type="text" placeholder="${inputComment}" id="inputComment" >

        <button type="submit"  class="btn" id="confirmButton">${confirmB}</button>
        <button type="button" class="btn cancel" onclick="closeForm()">${closeB}</button>


    </form>
</div>
<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";


    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }

    function submitForm() {
        var inputPhone = document.getElementById("inputPhone");
        var inputName = document.getElementById("inputName");
        var phoneReg = new RegExp("^(\\+375|80|375)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$");
        var check = true;

        $.ajax({

            url : 'view/CallbackComponentController/test-name',
            async: false,
            data: {
                name: inputName.value.toString()
            },

            success : function(data) {
                if(data == "Bad") {

                    inputName.style.border = "1px solid red";
                    check = false;
                }
                else {
                    inputName.style.border = "0px";

                }


            }

        });

        if(!phoneReg.test(inputPhone.value.toString())) {
            inputPhone.style.border = "1px solid red";
            check = false;
        }
        else {
            inputPhone.style.border = "0px";
        }

        if(check == true) {
            confirmCallback();
        }

    }



    function confirmCallback() {
        var customerPhone = document.getElementById("inputPhone").value.toString();
        var customerName = document.getElementById("inputName").value.toString();
        var customerComment = document.getElementById("inputComment").value.toString();
        $.ajax({

            url : 'view/CallbackComponentController/confirm-callback',
            data: {
                phone: customerPhone,
                name: customerName,
                comment: customerComment
            },

            success : function(data) {
                if(data == "Okay") {
                    document.getElementById("headText").style.display = "none";
                    document.getElementById("inputName").style.display = "none";
                    document.getElementById("textForInputName").style.display = "none";
                    document.getElementById("inputPhone").style.display = "none";
                    document.getElementById("textForInputPhone").style.display = "none";
                    document.getElementById("inputComment").style.display = "none";
                    document.getElementById("textForInputComment").style.display = "none";
                    document.getElementById("confirmButton").style.display = "none";
                    document.getElementById("confirmationText").style.display = "block";
                    document.getElementById("openButton").style.display = "none";
                }
            }
        });
    }
</script>
</body>


