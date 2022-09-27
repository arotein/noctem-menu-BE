package noctem.menuService.global.enumeration;

import noctem.menuService.global.common.CommonException;
import org.springframework.http.HttpStatus;

public enum Grade {
    TALL("TALL"),
    GRANDE("GRANDE"),
    VENTI("VENTI");

    private String value;

    Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Grade findInstance(String str) {
        switch (str.strip()) {
            case "TALL":
                return Grade.TALL;
            case "GRANDE":
                return Grade.GRANDE;
            case "VENTI":
                return Grade.VENTI;
            default:
                throw CommonException.builder().errorCode(3005).httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }
}
