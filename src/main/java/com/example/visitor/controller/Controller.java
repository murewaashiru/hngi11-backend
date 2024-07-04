package com.example.visitor.controller;

import com.example.visitor.dto.ResponseDto;
import com.maxmind.geoip2.exception.GeoIp2Exception;
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

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;

import static com.example.visitor.utils.HelperUtils.getClientCity;
import static com.example.visitor.utils.HelperUtils.getClientIp;

@RestController
@RequiredArgsConstructor
public class Controller {
    @GetMapping(value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getVisitorGreeting(@RequestParam String name, HttpServletRequest httpServletRequest) throws ServerErrorException, IOException, GeoIp2Exception, InvalidKeyException {

        if(name == null){
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.BAD_REQUEST);
        }
        var location = getClientCity(httpServletRequest);
        var response = new ResponseDto(getClientIp(httpServletRequest), location, "Hello, "+name+"!, the temperature is 11 degrees Celcius in "+ location);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}