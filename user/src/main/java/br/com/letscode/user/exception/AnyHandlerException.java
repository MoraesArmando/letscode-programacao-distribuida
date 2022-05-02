package br.com.letscode.user.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AnyHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadRequest.class})
    public ResponseEntity<ErroMsg> handlerExceptionCodigoUtilizado(Exception e, WebRequest request){
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();
        ErroMsg erroMsg = new ErroMsg(errorDescription);
        return new ResponseEntity<>(erroMsg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnauthorizedRequest.class})
    public ResponseEntity<ErroMsg> handlerExceptionUnauthorized(Exception e, WebRequest request){
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();
        ErroMsg erroMsg = new ErroMsg(errorDescription);
        return new ResponseEntity<>(erroMsg, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}