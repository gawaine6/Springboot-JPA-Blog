package com.k.blog.test;

import com.k.blog.model.Member;
import com.k.blog.model.RoleType;
import com.k.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    // 컨트롤러가 메모리에 뜰 때 repository도 같이 띄워준다, 의존성 주입(DI)
    private MemberRepository memberRepository;

    // http://localhost:8090/dummy/join (요청)
    // http의 body에 memberName, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(Member member) {
        // 아이디, 비밀번호, 이메일만 받자
        System.out.println("member.getId() = " + member.getId());
        System.out.println("member.getName() = " + member.getMemberName());
        System.out.println("member.getPass() = " + member.getPassword());
        System.out.println("member.getEm() = " + member.getEmail());
        System.out.println("member.getRole() = " + member.getRole());
        System.out.println("member.getCreateDate() = " + member.getCreateDate());

        // member.setRole("user"); // 근데 이렇게 하면 오타날 수도 있잖아, 그래서 enum 이라는 걸 사용해보겠습니다
        member.setRole(RoleType.USER);
        memberRepository.save(member);
        return "join complete";
    }

    @GetMapping("/dummy/member/{id}")
    // {id} 주소로 파라미터를 전달 받는다
    // findById를 보면 반환타입이 Optional 이다, 이거는 결과가 null일 수도 있기 때문에 Optional로 감싸주는거
    public Member detail(@PathVariable Long id) {
        /*
        만약에 데이터베이스에 값이 없다면 orElseGet을 실행한다
        그래서 빈 객체를 하나 새로 만들어준다, 그러면 null이 아니게 됨
        이거 말고 좀 더 선호되는 방식은 밑에 있는거, 근데 이게 더 어렵네
        이 부분은 영한샘꺼가 더 쉬운거 같은데
        return memberRepository.findById(id).orElseGet(new Supplier<Member>() {
            @Override
            public Member get() {
                return new Member();
            }
        });
        */
        Member member = memberRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("유저가 없다");
            }
        });
        /*
        람다식으로 표현하면 위에 처럼 Supplier 넣고 이런 거 안해주고 걍 익명으로 넘겨줄 수 이쑴, 이게 훨 편하긴 해
        Member member = memberRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("유저가 없다링 없다링");
        });
        */
        return member;
    }

    @GetMapping("/dummy/member")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    // 1페이지당 2건의 데이터 리턴받기
    @GetMapping("/dummy/member/page")
    public List<Member> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Member> members = memberRepository.findAll(pageable).getContent();
        return members;
    }

    @Transactional
    @PutMapping("/dummy/member/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member requestMember) { // key:value 값이 아닌 데이터를 넘겨줄 때는 RequestBody를 쓴다
        // json 데이터를 요청 -> Java Object(MessageConverter의 Jackson 라이브러리가)로 변환
        System.out.println("id = " + id);
        System.out.println("requestMember.getPassword() = " + requestMember.getPassword());
        System.out.println("requestMember.getEmail() = " + requestMember.getEmail());
        /*
        save함수는 id를 전달하지 않으면 insert를 해주고
        save함수는 id를 전달하면 해당 id에 대한 데이터가 있을 때 update를 해주고
        save함수는 id를 전달하면 해당 id에 대한 데이터가 없을 때 insert를 해준다
        save를 사용해서 update를 하게 되면 내가 수정한거 말고 다른 부분은 null로 바뀌는 대참사가 발생한다
        그래서 이 때는 해당 id에 속한 데이터를 찾아서 그거만 바꿔주는 방식을 사용한다, 좀 귀찮다
        저런 개짓거리 때문에 생기는 실수를 막기 위해 @Transactional 이라는걸 사용한다
        update할 때 저거 사용하면 save함수 안 써도 됨 개이득
         */
        Member member = memberRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        member.setPassword(requestMember.getPassword());
        member.setEmail(requestMember.getEmail());
        // memberRepository.save(member);

        // 더티체킹
        return member;
    }

    @DeleteMapping("/dummy/member/{id}")
    public String delete(@PathVariable Long id) {
        try {
            memberRepository.deleteById(id);
        } catch (Exception e) {
            return "No Exist member Id";
        }
        return "delete Complete";
    }
}

