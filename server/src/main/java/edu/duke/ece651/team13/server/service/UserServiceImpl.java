package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.UserEntity;
import edu.duke.ece651.team13.server.repository.UserRepository;
import edu.duke.ece651.team13.shared.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    final UserRepository repository;

    @Autowired
    final BCryptPasswordEncoder passwordEncoder;

    private String encode(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public UserEntity createUser(String fullName, String email, String password) {
        UserEntity user = new UserEntity();
        user.setFullName(fullName);
        user.setEmail(email);
        System.out.println(password);
        user.setPassword(encode(password));
        return repository.save(user);
    }

    @Override
    public UserEntity createUser(RegisterRequest registerRequest) {
        return createUser(registerRequest.getFullName(), registerRequest.getEmail(), registerRequest.getPassword());
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String isUserPresent(RegisterRequest registerRequest){
        Optional<UserEntity> existingUserEmail = repository.findByEmail(registerRequest.getEmail());
        if(existingUserEmail.isPresent()){
            return "An account with the same email already exists!";
        }
        return null;
    }

    @Override
    public UserEntity updateUserPassword(Long id, String password) {
        UserEntity user = getUserById(id);
        user.setPassword(encode(password));
        return repository.save(user);
    }

    @Override
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }
}
