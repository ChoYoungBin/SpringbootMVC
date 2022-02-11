package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;


@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "hello-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }

    @GetMapping("/mapping-get")
    public String getHelloBasic() {
        log.info("hello-get-basic");
        return "ok";
    }

    //resouce에 식별자를 넣는 스타일을 최근에 선호한다.
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mapping Path userId={}", data);

        return "ok";
    }

    @GetMapping("/mapping/number/{userNumber}")
    public String getMappingPath(@PathVariable String userNumber) {
        log.info("pathVariable is same as path = {}", userNumber);

        return "OK";
    }


    @GetMapping(value = "/mapping/header", headers = "mode=debug")
    public String getMappingHeader() {
        log.info("특정 헤더가 있어야 호출가능하게 세팅");

        return "OK";
    }

    @PostMapping(value = "/mapping-consume", consumes = APPLICATION_JSON_VALUE)
    public String getPostContentTypeMapping() {
        log.info("Header에 특정 ContentType 이 있어야 호출됨.");

        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = TEXT_HTML_VALUE)
    public String getPostProduce() {
        log.info("Request Header에 특정 accept가 produce와 일치해야 호출됨.");

        return "ok";
    }

}
