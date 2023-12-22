package com.uax.accesodatos.dto;

import java.util.ArrayList;

public class ListaResponseBuscadorDto {
	public String searchType;
	public String expression;
	public String errorMessage;

	public ArrayList<ResponseBuscadorDto> results;

	public ArrayList<ResponseBuscadorDto> getResults() {
		return results;
	}

	public void setResults(ArrayList<ResponseBuscadorDto> listaBuscador) {
		this.results = listaBuscador;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
