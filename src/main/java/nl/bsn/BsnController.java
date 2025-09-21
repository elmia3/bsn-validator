package nl.bsn;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bsn")
@CrossOrigin // allow simple local testing from another origin if needed
public class BsnController {

    private final BsnValidator validator = new BsnValidator();

    @GetMapping("/validate")
    public Map<String, Object> validate(@RequestParam String value) {
        boolean valid = validator.isValid(value);
        return Map.of("bsn", value, "valid", valid);
    }
}
