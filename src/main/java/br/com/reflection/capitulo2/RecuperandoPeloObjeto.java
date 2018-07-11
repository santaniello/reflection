package br.com.reflection.capitulo2;

/*
*
* Normalmente, essa forma de recuperar a classe é utilizada em métodos mais
* gerais que recebem um Object como parâmetro e utiliza reflexão para conhecer
* mais sobre a classe e saber quais métodos invocar ou quais atributos acessar. Isso
* possibilita que objetos de qualquer classe possam ser passados como parâmetro e
* utilizados pela lógica do método.
*
* A listagem a seguir mostra um exemplo da recuperação da classe a partir do ob-
* jeto. O tipo genérico retornado pelo método getClass() será sempre Class<?
* extends Tipo> , onde “Tipo” representa o tipo da variável na qual o método está
* sendo invocado. Isso faz sentido, pois uma variável de um determinado tipo pode
* armazenar objetos de um subtipo. No exemplo, o objeto é do tipo Integer , mas
* está sendo atribuído a uma variável do tipo Number . Sendo assim, apesar do tipo
* retornado pelo getClass() ser Class<? extends Number> , ao imprimir o
* nome do objeto Class obtido, será impresso no console “java.lang.Integer”.
*
* */
public class RecuperandoPeloObjeto {
    public static void main(String[] args) {
        Number object = new Integer(100);
        Class<? extends Number> c = object.getClass();
        System.out.println(c.getName());
    }
}
