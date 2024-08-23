package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springboot.entity.User;
import springboot.model.LoginUserRequest;
import springboot.model.TokenResponse;
import springboot.repository.UserRepository;
import springboot.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .ExpiredAt(user.getTokenExpiredAt())
                    .build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password wrong");
        }
    }
    private Long next30days(){
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
