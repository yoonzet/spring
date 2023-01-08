package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // 정적컨텐츠 방식(파일을 그대로 내보냄)
    public  String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // resource/templates/hello.html찾아서 랜더링함.
    }

    @GetMapping("hello-mvc") //템플릿으로 데이터를 프로그래밍해서 전달해줌
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") //http://localhost:8080/hello-string?name=hi
    @ResponseBody //http의 바디에 아래 데이터를 직접 넣어주겠다라는 의(view를 안거침)
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api") // json으로 만드는 방식 즉 객체를 반환하는 방식(가장 많이 쓰임)
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 팁!: 커맨드+쉬프트+엔터 하면 자동으로 닫아줌
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        // 커맨드 + n 눌러서 getter and setter 클릭
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
