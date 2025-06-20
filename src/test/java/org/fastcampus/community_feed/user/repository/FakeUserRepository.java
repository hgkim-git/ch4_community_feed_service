package org.fastcampus.community_feed.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;

public class FakeUserRepository implements UserRepository {
    // RDBMS 처럼 동작하도록 인메모리 DB 만들기
    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            store.put(user.getId(), user);
            return user;
        }
        long id = store.size() + 1;
        User newUser = new User(id, user.getUserInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
