package br.com.reflection.capitulo3;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@interface Anotacao{
    Class value();
}

/*
* a listagem a seguir mostra um código que recupera as anotações de um elemento
* e imprime no console com seus atributos. Observe que o método recebe um AnnotatedElement como parâmetro,
  permitindo que receba qualquer classe que represente um elemento que possa ter
  anotações. Ao recuperar a instância de Class referente a anotação utilizando
  annotationType() , o método imprime o nome da anotação e em seguida itera
  pelos seus métodos imprimindo o nome e o valor retornado. Observe que utili-
  zando getDeclaredMethods() recuperamos somente os métodos declarados
  na anotação, ou seja, os que definem atributos.
* */

@Metadado2(nome="teste",numero=34)
@Anotacao(String.class)
public class ImprimeAnotacoes {
    public static void imprimeAnotacoes(AnnotatedElement ae) throws Exception {
        Annotation[] ans = ae.getAnnotations();
        for (Annotation a : ans) {
            Class<?> c = a.annotationType();
            System.out.println("@"+c.getName());
            for(Method m : c.getDeclaredMethods()){
                Object o = m.invoke(a);
                System.out.println(" |->"+m.getName()+"="+o);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        imprimeAnotacoes(ImprimeAnotacoes.class);
    }
}
