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

    if (inputName.value.toString().length < 2 || inputName.value.toString().length > 30) {
        inputName.style.border = "1px solid red";
        check = false;
    } else {
        inputPhone.style.border = "0px";
    }

    if (!phoneReg.test(inputPhone.value.toString())) {
        inputPhone.style.border = "1px solid red";
        check = false;
    } else {
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
        type: "POST",
        url : 'view/CallbackComponentController/confirm-callback',
        data: {
            phone: customerPhone,
            name: customerName,
            comment: customerComment
        },

        success : function(data) {
            if(data == true) {
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