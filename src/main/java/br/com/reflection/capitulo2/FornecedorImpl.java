package br.com.reflection.capitulo2;

public class FornecedorImpl implements Fornecedor{
    private  String nome;
    private  Integer idade;

    public FornecedorImpl(String nome){
        this.nome = nome;
    }

    public FornecedorImpl(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome(){
        return this.nome;
    }

    public Integer getIdade(){
        return this.idade;
    }
}
