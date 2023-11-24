package br.com.fiap.corecraft.model;

public class Categoria {

	
	private Long id_cat;
	private User user;
	
	private String nome_categ;

	public Long getId_cat() {
		return id_cat;
	}

	public void setId_cat(Long id_cat) {
		this.id_cat = id_cat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome_categ() {
		return nome_categ;
	}

	public void setNome_categ(String nome_categ) {
		this.nome_categ = nome_categ;
	}

	public Categoria(Long id_cat, User user, String nome_categ) {
		this.id_cat = id_cat;
		this.user = user;
		this.nome_categ = nome_categ;
	}

	public Categoria() {
		super();

	}
	
	
	
	
}
