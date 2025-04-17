package br.com.login_base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.login_base.entity.User;
import br.com.login_base.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private UserRepository userRepository;
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Value("${user.name.admin}")
	private String NAME_ADMIN;

	@Value("${user.email.admin}")
	private String EMAIL_ADMIN;

	@Value("${user.password.admin}")
	private String PASSWORD_ADMIN;

	@Override
	public void run(ApplicationArguments args) {
		var user = userRepository.findByEmail(EMAIL_ADMIN);
		
		if (user.isPresent()) {
			System.out.println("\n\n ===== Usuário admin já cadastrado ===== \n\n");
			return;
		}
		
		userRepository.save(new User(EMAIL_ADMIN, NAME_ADMIN, PASSWORD_ENCODER.encode(PASSWORD_ADMIN)));
		System.out.println("\n\n ===== Usuário admin cadastrado com sucesso ===== \n\n");
	}
}
