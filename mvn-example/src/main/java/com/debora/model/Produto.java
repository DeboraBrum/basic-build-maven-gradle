package com.debora.model;

public class Produto {
    private String nome;
    private Double valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", valor=" + valor + "]";
    }
    
}
