package br.com.cobranca.model;

public enum StatusTitulo {
	PENDENTE(0, "Pendente"),
	RECEBIDO(1, "Recebido");
	
	private int id;
	private String descricao;
	
	StatusTitulo(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
