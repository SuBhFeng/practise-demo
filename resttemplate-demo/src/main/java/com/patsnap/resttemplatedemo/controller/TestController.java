package com.patsnap.resttemplatedemo.controller;

import com.patsnap.resttemplatedemo.domaim.Notice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getRequest1")
    public ResponseEntity<?> getTest1(){
        Notice notice = new Notice();
        notice.setMsg("this is noargs getRequest");
        notice.setStatus("200");
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @GetMapping("/getRequest2")
    public ResponseEntity<?> getTest2(String msg,String status){
        Notice notice = new Notice();
        notice.setMsg(msg);
        notice.setStatus(status);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping("/postRequest1")
    public ResponseEntity<?> postTest1(HttpServletRequest request){
        String id = request.getParameter("id");
        String version = request.getHeader("api-version");
        return new ResponseEntity<>("call a postRequest1\n"+"id:"+id+"\napi-version:"+version,HttpStatus.OK);
    }

    @PostMapping("/postRequest2")
    public ResponseEntity<?> postTest2(HttpServletRequest request){
//        Map<String, String[]> parameterMap = request.getParameterMap();
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>("call a postRequest1\n"+"id:"+ sb.toString(),HttpStatus.OK);
    }


}
