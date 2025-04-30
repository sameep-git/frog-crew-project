package edu.tcu.cs.frogcrew.security;

import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.endpoint.base-url}/auth/login")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public Result getLoginInfo(Authentication authentication) {
        LOGGER.debug("Authenticated user: {}", authentication.getName());
        return new Result(true, StatusCode.SUCCESS, "Login success", this.authService.createLoginInfo(authentication));
    }

    @GetMapping()
    public Result validateInviteToken(@PathVariable String token) {
        boolean isValid = this.authService.validateInviteToken(token);

        if (isValid) {
            Map<String, String> tokenData = new HashMap<>();
            tokenData.put("token", token);
            return new Result(true, StatusCode.SUCCESS, "Token valid", null);
        } else {
            return new Result(false, StatusCode.NOT_FOUND, "Token not valid", null);
        }
    }
}