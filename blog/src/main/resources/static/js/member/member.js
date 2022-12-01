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
            //alert("로그인 성공");
        },
        error : function(request) {
            alert(request.responseText);
        }
    });

}