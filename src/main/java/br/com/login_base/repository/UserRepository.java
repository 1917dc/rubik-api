package br.com.login_base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.login_base.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

}
