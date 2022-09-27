package noctem.menuService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/api/menu-service")
public class HelloController {
    private final List<String> list = new ArrayList<>();
    @Value("${global.value}")
    private String value;

    @GetMapping("/yml")
    public String yml() {
        return value;
    }
}
