let main = {
    init:function () {
        $("#btn-save").on("click", () => {
            // function(){}을 안 쓰고 ()=>{}를 사용한 이유는 this를 바인딩하기 위해서
            // function(){} 쓰려면 위에 let _this = this; 해줘야됨
            this.save();
        });
    },

    save:function () {
        let data = {
        // alert('hello');
            memberName: $('#memberName').val(),
            password: $('#password').val(),
            email: $('#email').val()
        };
        $.ajax({
            type: "post",
            url: "/api/member",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
            dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열이다, 근데 생긴 게 json 이라면 자바스크립트 객체로 변환
        }).done(function (result) {
            console.log(result);
            alert("JOIN Complete");
            location.href = "/";
        }).fail(function () {
            alert("SSIBAL");
        });
    }
}

main.init()