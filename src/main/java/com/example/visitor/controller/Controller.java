package com.example.visitor.controller;

import com.example.visitor.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

@RestController
@RequiredArgsConstructor
public class Controller {
    @GetMapping(value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getVisitorGreeting(@RequestParam String name) throws BadRequestException, ServerErrorException {

        if(name == null){
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.BAD_REQUEST);
        }
        var response = new ResponseDto("127.0.0.1", "New York", "Hello, "+name+"!, the temperature is 11 degrees Celcius in New York");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}