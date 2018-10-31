package graphql.service;

import graphql.exception.NotFoundException;
import graphql.model.User;
import graphql.repository.UserRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "user")
    public User findById(@GraphQLArgument(name = "id") String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found", id));
    }

    @GraphQLQuery(name = "user")
    public User findByUsername(@GraphQLArgument(name = "username") String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found", username));
    }

    @GraphQLMutation(name = "newUser")
    public User saveUser(@GraphQLArgument(name = "user") User user) {
        return userRepository.save(user);
    }

    @GraphQLMutation(name = "deleteUser")
    public void deleteUser(@GraphQLArgument(name = "id") String id) {
        userRepository.deleteById(id);
    }
}
