package br.com.fiap.corecraft.model;

public class Workout {

	private Long id_work;
	private String resultado;
	private User user;
	private Categoria categoria;
	public Long getId_work() {
		return id_work;
	}
	public void setId_work(Long id_work) {
		this.id_work = id_work;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Workout(Long id_work, String resultado, User user, Categoria categoria) {
		this.id_work = id_work;
		this.resultado = resultado;
		this.user = user;
		this.categoria = categoria;
	}
	public Workout() {
		super();
	
	}
	
	
	
	
	
	
	
}
