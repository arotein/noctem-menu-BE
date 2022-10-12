package noctem.menuService.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {
    private T data;
    private Integer errorCode;
    private HttpStatus httpStatus;
    private String serviceName = "${spring.application.name}";
}
