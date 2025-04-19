package dev.truetechhack.adapter.rest.user;

import dev.truetechhack.app.api.user.GetAllUserInbound;
import dev.truetechhack.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest-api/supply")
@RequiredArgsConstructor
public class UserController {
    private final GetAllUserInbound getAllUserInbound;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return getAllUserInbound.execute();
    }
}
