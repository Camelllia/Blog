var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');

var login = function () {

    var param = {
        email : $("#userEmail").val(),
        password : $("#userPw").val()
    }

    $.ajax({
        url : "/login",
        type : "POST",
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        data : JSON.stringify(param),
        success : function(data) {

        },
        error : function() {
            alert("Error Occurred");
        }
    });

}