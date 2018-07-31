package br.com.reflection.capitulo3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
*  A listagem a seguir mostra um exemplo de código onde uma anotação é
*  recuperada e seus atributos impressos no console. Antes de imprimir a anotação,
*  o método isAnnotationPresent() é utilizado para confirmar a presença da
*  anotação. Observe que quando o método getAnnotation() é invocado, ele
*  recebe a classe da anotação como parâmetro e utiliza seu tipo genérico para inferir
*  o tipo do retorno, que é o próprio tipo da anotação. A partir dessa instância com
*  o tipo da anotação, é possível obter os seus atributos invocando métodos com seus
*  nome, conforme mostrado no exemplo.
*
*  Os outros métodos definidos em AnnotatedElement para recuperação
*  de anotações retornam a lista de anotações de um elemento. O método
*  getAnnotations() irá retornar uma lista com todas as anotações de um deter-
*  minado elemento de código e o método getDeclaredAnnotations() irá retor-
*  nar somente as anotações declaradas no elemento, excluindo as com @Inherited
*  que foram herdadas da superclasse. Como a herança de anotações funciona somente
*  para classes, para outros tipos de elemento o retorno dos dois métodos será sempre
*  o mesmo.
* */

//definição da anotação
@Retention(RetentionPolicy.RUNTIME)
@interface Metadado2 {
    String nome();
    int numero();
}

//Uso e recuperação de anotação
@Metadado2(nome="classe", numero=17)
public class RecuperaAnotacao {
    public static void main(String[] args) {
        Class<RecuperaAnotacao> c = RecuperaAnotacao.class;
        if(c.isAnnotationPresent(Metadado2.class)){
            Metadado2 m = c.getAnnotation(Metadado2.class);
            System.out.println("Propriedade nome = "+ m.nome());
            System.out.println("Propriedade numero ="+ m.numero());
        }
    }


}
