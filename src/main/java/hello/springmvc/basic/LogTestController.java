package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // 이렇게 직접 선언해서도 쓸 수 있다 @Slf4j 를 쓰기 전에
    // private final Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //{}를 써야하는이유는 로그레벨과 관계없이 String 연산이 일어나는 현상을 방지하기 위함
        //log.trace("trace log=" + name); 이거는 안됨!!

        log.trace("trace log={}", name);
        log.debug("debug log{}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
