package com.creditcard.account.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class) public ResponseEntity<Map<String,Object>> h1(AccountNotFoundException e){return build(HttpStatus.NOT_FOUND,e.getMessage());}
    @ExceptionHandler(DuplicateAccountException.class) public ResponseEntity<Map<String,Object>> h2(DuplicateAccountException e){return build(HttpStatus.CONFLICT,e.getMessage());}
    @ExceptionHandler(Exception.class) public ResponseEntity<Map<String,Object>> h3(Exception e){return build(HttpStatus.INTERNAL_SERVER_ERROR,"Error");}
    private ResponseEntity<Map<String,Object>> build(HttpStatus s,String m){Map<String,Object> r=new HashMap<>();r.put("timestamp",LocalDateTime.now());r.put("status",s.value());r.put("message",m);return ResponseEntity.status(s).body(r);}
}
