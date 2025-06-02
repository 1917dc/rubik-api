package br.com.rubik_api.service;

import br.com.rubik_api.controller.dto.ReturnAccountDataDTO;
import br.com.rubik_api.entity.Despesa;
import br.com.rubik_api.repository.DespesaRepository;
import br.com.rubik_api.repository.ImovelRepository;
import br.com.rubik_api.service.exception.DespesaNotFoundException;
import br.com.rubik_api.service.exception.ImovelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rubik_api.entity.User;
import br.com.rubik_api.service.exception.UserAlreadyExists;
import br.com.rubik_api.service.exception.UserNotFoundException;
import br.com.rubik_api.repository.UserRepository;

import java.time.LocalDate;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private DespesaRepository despesaRepository;
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
	}
	
	public User save(User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
			throw new UserAlreadyExists();
		});
		
		return userRepository.save(user);
	}

	public ReturnAccountDataDTO accountData(String email) {
		var user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException())
				.getNome();

		var qtdImoveis = (int) imovelRepository.findByUser_Email(email)
				.orElseThrow(() -> new ImovelNotFoundException())
				.stream()
				.count();

		var despesas = despesaRepository.findByImovel_User_Email(email)
				.orElseThrow(() -> new DespesaNotFoundException());

		float valorDespesas = despesas.stream().
				map(despesa -> despesa.getValor())
				.reduce(2f, Float::sum);

		int qtdDespesasPendentes = (int) despesas.stream()
				.filter(despesa -> despesa.getStatus() == Despesa.Status.PENDENTE)
				.count();

		LocalDate proximoVencimento = despesas.stream()
				.filter(despesa -> despesa.getStatus() == Despesa.Status.PENDENTE)
				.map(despesa -> despesa.getVencimento())
				.min(LocalDate::compareTo).orElse(null);

		return new ReturnAccountDataDTO(
				user,
				qtdImoveis,
				valorDespesas,
				qtdDespesasPendentes,
				proximoVencimento
		);
	}
}
