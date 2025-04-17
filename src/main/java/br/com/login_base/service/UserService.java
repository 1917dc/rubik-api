package br.com.login_base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.login_base.entity.User;
import br.com.login_base.exception.UserAlreadyExists;
import br.com.login_base.exception.UserNotFoundException;
import br.com.login_base.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User findByUsername(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
	}
	
	public User save(User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
			throw new UserAlreadyExists();
		});
		
		return userRepository.save(user);
	}
}
