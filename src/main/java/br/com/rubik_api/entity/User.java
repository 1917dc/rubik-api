package br.com.rubik_api.entity;

import jakarta.persistence.*;
import java.util.List;
import br.com.rubik_api.entity.imovel.Imovel;

@Entity
@Table(name = "tb_users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email")
	private String email;

	@Column(name = "nome")
	private String nome;

	@Column(name = "senha")
	private String senha;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private List<Imovel> imoveis;

	public User() {}

	public User(String email, String nome, String senha) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public User(String email, String nome, String senha, List<Imovel> imoveis) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.imoveis = imoveis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}
}
