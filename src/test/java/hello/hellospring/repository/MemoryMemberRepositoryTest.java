package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach //어떤 메소드가 끝날때마다 동작을 해주는역할
    public void afterEach(){
        repository.clearStore(); //하나의 테스트가 끝나면 저장소에 있는 내용들이 지워진다(테스트들간의 의존성 안생기도록 한다.)
    }
    @Test
    public  void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result); // 비교방법 1
       assertThat(member).isEqualTo(result); //비교방법 2(좀더 이해하기 쉽게 읽히는 방법)
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //  repository.findAll() 치고 옵션+커멘드+v 하면 자동완성

        assertThat(result.size()).isEqualTo(2);
    }


}
