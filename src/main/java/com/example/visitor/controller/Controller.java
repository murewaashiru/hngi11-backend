package com.example.visitor.controller;

import com.example.visitor.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import static com.example.visitor.utils.HelperUtils.getClientIp;

@RestController
@RequiredArgsConstructor
public class Controller {
    @GetMapping(value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getVisitorGreeting(@RequestParam String name, HttpServletRequest httpServletRequest) throws ServerErrorException {

        if(name == null){
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.BAD_REQUEST);
        }
        var response = new ResponseDto(getClientIp(httpServletRequest), "New York", "Hello, "+name+"!, the temperature is 11 degrees Celcius in New York");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    private static HttpServletRequest request;
//
//    @Autowired
//    public void setRequest(HttpServletRequest request) {
//        this.request = request;
//    }
//    public static String getClientIp() {
//        String remoteIp = "";
//
//        if (request != null) {
//            remoteIp = request.getHeader("X-FORWARDED-FOR");
//        }
//        if ((remoteIp == null || "".equals(remoteIp)) && request != null) {
//            remoteIp = request.getRemoteAddr();
//        }
//
//        //clean up Ip
//        remoteIp = checkIp(remoteIp);
//        return remoteIp;
//    }
//    public static String checkIp(String ipAdd) {
//        if (ipAdd.contains(",")) {
//            ipAdd = ipAdd.split(",")[0].trim();
//        }
//        return ipAdd;
//    }
}