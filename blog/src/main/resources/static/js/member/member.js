var login = function () {

    var param = {
        email : $("#userEmail").val(),
        password : $("#userPw").val()
    }

    $.ajax({
        url : "/login",
        type : "POST",
        contentType: "application/json",
        data : JSON.stringify(param),
        success : function(data) {
            alert(data);
        },
        error : function(request) {
            alert(request.responseText);
        }
    });

}

var join = function () {

    var param = {
        username : $("#userName").val(),
        password : $("#userPw").val(),
        passwordRepeat : $("#userPwRepeat").val(),
        email : $("#userEmail").val(),
        age : $("#userAge").val()
    }

    $.ajax({
        url : "/join",
        type : "POST",
        contentType: "application/json",
        data : JSON.stringify(param),
        success : function() {
            alert("정상적으로 가입되었습니다");
            location.href= "/";
        },
        error : function(request) {
            alert(request.responseText);
        }
    });

}