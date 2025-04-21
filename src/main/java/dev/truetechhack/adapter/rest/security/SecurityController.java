package dev.truetechhack.adapter.rest.security;

import dev.truetechhack.adapter.rest.security.dto.UserSignInDto;
import dev.truetechhack.app.api.security.SignInInbound;
import dev.truetechhack.app.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-api/security")
@RequiredArgsConstructor
public class SecurityController {
    private final SignInInbound signInInbound;

    @GetMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody UserSignInDto userSignInDto) {
        try {
            signInInbound.execute(userSignInDto.email(), userSignInDto.password());
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Sign in successful");
    }
}
