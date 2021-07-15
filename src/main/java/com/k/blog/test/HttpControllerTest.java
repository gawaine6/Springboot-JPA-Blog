package com.k.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder()
                .username("saladin")
                .password("12345")
                .email("saladin@saladin.com")
                .build();
        // 만약에 Member에 선언된 생성자처럼 전부 다 들어가있는거 말고 id만 빼고 다른 걸 다 넣는다면 어떨까
        // 그러면 Member에서 id만 뺀 생성자를 새로 만들어줘야하는데 그렇게 하지 않고 @Builder 패턴을 사용하는 방법이 있다
        // 객체.builder() 해서 내가 넣고 싶은 것만 골라서 쇽쇽 넣어주면 된다(순서 상관 없다)

        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());
        return "lombokTest Complete";
    }
    // GetMapping 으로 뭔가 받고 싶을 때는 @RequestParam
    // 내가 특정 파라미터를 받고 싶다면 @RequestParam 하고 받고 싶은 거를 넣으면 된다 ex) getTest(@RequestParam int id)
    // 근데 만약에 여러 개를 받는다고 하면 여러 번 써줘야된다, 얼마나 귀찮냐
    // 그래서 내가 받고 싶은 거의 해당 객체를 받고, 거기서 get 해오는 방법도 있다
    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get request : " + m.getId() + m.getUsername();
    }

    @GetMapping("/http/hello")
    public String getHello(Hello h) {
        return "hello get : " + h.getId() + h.getHe() + h.getGo();
    }
    /*
    이건 컨트롤러 안에서 값 설정해서 넘겨주는 거, 그냥 되는가 한번 해본거임, 근데 되네
    @GetMapping("/http/get")
    public String getTest() {
        Member m = new Member();
        m.setId(1);
        m.setPassword("123");
        return "get request : " + m.getId() + ", " +  m.getPassword();
    }
     */

    // PostMapping 으로 뭔가 받고 싶을 때는 @RequestBody
    @PostMapping("/http/post") // raw데이터 - text/plain, json데이터 - application/json
    // 근데 걍 String 으로 보내면 문자열 그대로 받아와버린다, 우리가 원하는 json 형태가 아니라는 소리
    // 그래서 @RequestBody 해주고 내가 받고 싶은 객체를 넣어버린다
    public String postTest(@RequestBody Member m) {
        return "post request : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put request : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete request";
    }

}
