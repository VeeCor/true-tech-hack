package dev.truetechhack.app.api.user;

import dev.truetechhack.domain.user.User;

import java.util.List;

public interface GetAllUserInbound {
    List<User> execute();
}
