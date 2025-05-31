package br.com.rubik_api.config;

import br.com.rubik_api.entity.User;
import br.com.rubik_api.service.UserService;
import br.com.rubik_api.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Value("${user.base.email}")
    private String email;

    @Value("${user.base.name}")
    private String name;

    @Value("${user.base.password}")
    private String password;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public void run(ApplicationArguments args) {
        try {
            userService.findByEmail(email);
            System.out.println("\n\n ===== Base user already exists ===== \n\n");
        } catch (UserNotFoundException e) {
            userService.save(new User(email, name, PASSWORD_ENCODER.encode(password)));
            System.out.println("\n\n ===== Base user created successfully ===== \n\n");
        }
    }
}