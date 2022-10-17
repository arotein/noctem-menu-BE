package noctem.menuService.global.exception;

import lombok.extern.slf4j.Slf4j;
import noctem.menuService.global.common.CommonException;
import noctem.menuService.global.common.CommonResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * errorCode: 3000~3999
 * 사용 가능 에러코드 3006 ~
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity methodNotAllowedExHandle(HttpRequestMethodNotSupportedException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CommonResponse.builder().errorCode(3002).build());
    }

    @ExceptionHandler
    public ResponseEntity illegalArgumentExceptionExHandle(IllegalArgumentException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(3003).build());
    }

    @ExceptionHandler
    public ResponseEntity constraintViolationExceptionExceptionExHandle(ConstraintViolationException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(3004).build());
    }

    @ExceptionHandler
    public ResponseEntity commonExHandle(CommonException ex) {
        log.warn("Exception Name = {}, Code = {}, Message = {}", ex.getClass().getName(), ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus())
                .body(CommonResponse.builder().errorCode(ex.getErrorCode()).build());
    }
}
