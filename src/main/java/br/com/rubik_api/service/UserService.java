package br.com.rubik_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rubik_api.entity.User;
import br.com.rubik_api.service.exception.UserAlreadyExists;
import br.com.rubik_api.service.exception.UserNotFoundException;
import br.com.rubik_api.repository.UserRepository;

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
