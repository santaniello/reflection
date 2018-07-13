package br.com.reflection.capitulo2;

import java.lang.reflect.Field;


/*
* Através da API Reflection é possível recuperar os atributos de uma classe, que
  são representados pela classe Field . A partir dessas referências dos atributos
  seus valores podem ser acessados e modificados. Existem dois métodos para
  retornar a lista de atributos de um classe: getFields() que retorna todos os
  atributos públicos e getDeclaredFields() que retorna todos os atributos que
  foram declarados naquela classe incluindo os que são privados. Para exemplificarmos
  a diferença entre esses dois métodos, considere o código apresentado na listagem seguinte.

* */

public class AcessoAtributo {
    private int atributoUm;
    public String atributoDois;
    public static void main(String[] args) {
        System.out.println("Retornado pelo getFields()");
        for(Field f : AcessoAtributo.class.getFields()){
            System.out.println(
                    "- "+f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println("Retornado pelo getDeclaredFields()");
        for(Field f : AcessoAtributo.class.getDeclaredFields()){
            System.out.println(
                    "- "+f.getType().getSimpleName() + " " + f.getName());


        }
    }
}

