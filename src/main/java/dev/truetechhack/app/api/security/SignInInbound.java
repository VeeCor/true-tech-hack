package dev.truetechhack.app.api.security;

import reactor.core.publisher.Mono;

public interface SignInInbound {
    void execute(String email, String password);
}
