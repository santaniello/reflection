package br.com.reflection.generatemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Produto {
    private String nome;
    private String categoria;
    private Double preco;
    private String descricao;
}