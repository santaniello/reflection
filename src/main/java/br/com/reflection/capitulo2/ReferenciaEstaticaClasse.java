package br.com.reflection.capitulo2;

/*
*    O código apresentado a seguir mostra como as referências estáticas a classes
*    podem ser utilizadas. Inicialmente é criada uma instância de Class que recebe
*    a instância referente a classe String , da qual é impresso o nome no console.
*    Pode não parecer muito útil utilizar referências estáticas da classe, pois se já sei
*    com qual classe vou trabalhar, para que preciso utilizar reflexão? Porém, essas refe-
*    rências estáticas são muito utilizadas quando precisamos passar uma instância de
*    Class como parâmetro para um método, como também é apresentado na listagem.
**/

import java.io.Serializable;

public class ReferenciaEstaticaClasse {
    public static void main(String[] args) {
        Class<String> classe = String.class;
        System.out.println(classe.getName());
        imprimeNomeDeQualquerClasse(Integer.class);
        imprimeNomeDeClasseEspecifica(TesteSerializable.class);
    }
    public static void imprimeNomeDeQualquerClasse(Class<?> classe){
        System.out.println("Chamado o método com " +classe.getName());
    }

    /*
    * Aceitamos como parâmetro somente
    * instâncias de Class de tipos que implementam a interface Serializable .
    * */
    public static void imprimeNomeDeClasseEspecifica(Class<? extends Serializable> classe){
        System.out.println("Chamado o método com " +classe.getName());
    }
}

class TesteSerializable implements Serializable{}