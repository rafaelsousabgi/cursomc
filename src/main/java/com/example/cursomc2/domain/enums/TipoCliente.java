package com.example.cursomc2.domain.enums;

public enum TipoCliente {
	
	/** cODICO PARA CRIAR UM TIPO ENUMERADO DE UMA CLASSE
	 * ELE SEGUE A SEQUENCIA ESCRITA, TEM QUE CRIAR O CONSTRUTOR PARA SANAR O ERRO**/
	
	PESSOAFISICA(1,"PESSOA FISICA"),
	PESSOAJURIDICA(2, "PESSOA JURIDICA");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	/**metodo static para verificar o codigo do cliente**/
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		/**erro caso informe um valor de codigo invalido**/
		throw new IllegalArgumentException("Id invalido:" + cod);
	}

}
