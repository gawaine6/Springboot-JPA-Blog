let main = {
    init:function () {
        $("#btn-save").on("click", () => {
            // function(){}을 안 쓰고 ()=>{}를 사용한 이유는 this를 바인딩하기 위해서
            // function(){} 쓰려면 위에 let _this = this; 해줘야됨
            this.save();
        });
        // $("#btn-login").on("click", () => {
        //     this.login();
        // });
    },

    save:function () {
        let data = {
        // alert('hello');
            title: $('#title').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: "post",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
            dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열이다, 근데 생긴 게 json 이라면 자바스크립트 객체로 변환
        }).done(function (result) {
            alert("Board Write Complete");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

    // login:function () {
    //     let data = {
    //         memberName: $('#memberName').val(),
    //         password: $('#password').val()
    //     };
    //     $.ajax({
    //         type: "post",
    //         url: "/api/login",
    //         data: JSON.stringify(data), // http body 데이터
    //         contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
    //         dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열이다, 근데 생긴 게 json 이라면 자바스크립트 객체로 변환
    //     }).done(function (result) {
    //         console.log(result);
    //         alert("LOGIN Complete");
    //         location.href = "/";
    //     }).fail(function (error) {
    //         alert("아니야 시발");
    //         alert(JSON.stringify(error));
    //     });
    // }
}

main.init()