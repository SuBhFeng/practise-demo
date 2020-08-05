package com.patsnap.resttemplatedemo.controller;

import com.patsnap.resttemplatedemo.domaim.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * getForObject()方法:
     * public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables){}
     * public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
     * public <T> T getForObject(URI url, Class<T> responseType)
     *
     * getForObject()其实比getForEntity()多包含了将HTTP转成POJO的功能，但是getForObject没有处理response的能力。
     * 因为它拿到手的就是成型的pojo。省略了很多response的信息。
     * @return
     */
    @GetMapping("/getRequest1")
    public String getRequest1(){
        Notice notice = restTemplate.getForObject("http://localhost:8081/test/getRequest1", Notice.class);
        return notice.toString();
    }

    /**
     * todo
     * @return
     */
    @GetMapping("/getRequest2")
    public String getRequest2(){
        Map<String, String> params = new HashMap<>();
        params.put("msg","this is has args getRequest");
        params.put("status","200");
        Notice notice = restTemplate.getForObject("http://localhost:8081/test/getRequest2?msg={msg}&status={status}", Notice.class,params);
        return notice.toString();
    }

    /**
     *
     * public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
     *             throws RestClientException {}
     * public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)
     *             throws RestClientException {}
     * public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException {}
     * @return
     */
    @PostMapping("/postRequest1")
    public ResponseEntity<?> postRequest(){
        String url = "http://localhost:8081/test/postRequest1";
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-version", "1.0");
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id", "1");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity;

    }

    @GetMapping("/exchange")
    public ResponseEntity<?> exchange(){

        String url = "http://localhost:8081/test/postRequest1";
        // headers
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.add("api-version","1.0");
        // body
        MultiValueMap<String,String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id","2");
        // HttpEntity
        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(requestBody, requestHeader);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return  response;
    }


}

