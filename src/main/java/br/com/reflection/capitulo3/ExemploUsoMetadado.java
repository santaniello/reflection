package br.com.reflection.capitulo3;

// Definição da anotação...
@interface Metadado {
    Class value();
    String tipo() default "";
}


public class ExemploUsoMetadado {

    @Metadado(String.class) //válido pois tipo tem valor default...
    public String atributo2;

    @Metadado(value=String.class, tipo="OK") //válido
    public String atributo3;

    @Metadado(value =String.class, tipo="OK")
    public String atributo4;

}
