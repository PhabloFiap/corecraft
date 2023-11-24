package br.com.fiap.corecraft.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class User {
	
	private Long id;
	
	@NotBlank(message="O nome  nao pode estar em branco")
	private String nome;
		
	@PastOrPresent(message="data de cadastro  nao deve ser maior que hoje")
	private LocalDate dt_cadastro;
	
	@NotBlank(message="email nao pode ser nulo")
	private String email;
	
	
	@NotBlank(message="senha do usuario nao pode ser nulo")
	private String senha;
	
	
	
	
	

	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getNome() {
		return nome;
	}






	public void setNome(String nome) {
		this.nome = nome;
	}






	public LocalDate getDt_cadastro() {
		return dt_cadastro;
	}






	public void setDt_cadastro(LocalDate dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public String getSenha() {
		return senha;
	}






	public void setSenha(String senha) {
		this.senha = senha;
	}






	public User() {
		super();
		// TODO Auto-generated constructor stub
	}






	public User(Long id, @NotBlank(message = "O nome  nao pode estar em branco") String nome,
			@PastOrPresent(message = "data de cadastro  nao deve ser maior que hoje") LocalDate dt_cadastro,
			@NotBlank(message = "email nao pode ser nulo") String email,
			@NotBlank(message = "senha do usuario nao pode ser nulo") String senha) {
		this.id = id;
		this.nome = nome;
		this.dt_cadastro = dt_cadastro;
		this.email = email;
		this.senha = senha;
	}



	








	
	
	

}
