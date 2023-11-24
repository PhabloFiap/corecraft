package br.com.fiap.corecraft.model;

public class Exercicio {

	private Long id_exercicio;
	private String nome_exercicio;
	private String desc_exercicio;
	private int repeticoes;
	private String dificuldade;
	private int series;
	private Categoria categoria;
	public Long getId_exercicio() {
		return id_exercicio;
	}
	public void setId_exercicio(Long id_exercicio) {
		this.id_exercicio = id_exercicio;
	}
	public String getNome_exercicio() {
		return nome_exercicio;
	}
	public void setNome_exercicio(String nome_exercicio) {
		this.nome_exercicio = nome_exercicio;
	}
	public String getDesc_exercicio() {
		return desc_exercicio;
	}
	public void setDesc_exercicio(String desc_exercicio) {
		this.desc_exercicio = desc_exercicio;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Exercicio(Long id_exercicio, String nome_exercicio, String desc_exercicio, int repeticoes,
			String dificuldade, int series, Categoria categoria) {
		this.id_exercicio = id_exercicio;
		this.nome_exercicio = nome_exercicio;
		this.desc_exercicio = desc_exercicio;
		this.repeticoes = repeticoes;
		this.dificuldade = dificuldade;
		this.series = series;
		this.categoria = categoria;
	}
	public Exercicio() {
		super();
	}
	
	
	
	
	
}
