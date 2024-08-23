package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springboot.entity.User;
import springboot.model.RegisterUserRequest;
import springboot.model.UpdateUserRequest;
import springboot.model.UserResponse;
import springboot.repository.UserRepository;
import springboot.security.BCrypt;

import java.util.Objects;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;


    public void register(RegisterUserRequest request){
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    public User getUserByUsername (String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    public UserResponse update(User user, UpdateUserRequest request){
        validationService.validate(request);

        if (Objects.nonNull(request.getName())){
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

}
