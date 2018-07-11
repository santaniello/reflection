package br.com.reflection.capitulo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
Para exemplificar a recuperação de informações da classe, abaixo segue um
exemplo de aplicação em que o usuário escreve o nome da classe no console e ele
imprime a hierarquia de classes e interfaces. No método main() o programa
lê o nome da classe utilizando a classe Scanner e obtém a instância de Class
com o método forName() . Em seguida, o método imprimirHierarquia() é
chamado passando a classe como parâmetro.
-------------------------------------------------------------------------------------------
O método getInterfaces() irá retornar as interfaces implementadas
caso o Class represente uma classe e as interfaces estendidas caso seja uma
interface.

* */
public class ObterInformacaoClasse {
    public static void main(String[] args) {
        System.out.println("Entre com o nome da classe que deseja informação:");
        Scanner in = new Scanner(System.in);
        String nomeClasse = in.nextLine();
        try {
            Class<?> c = Class.forName(nomeClasse);
            imprimirHierarquia(c, 1);
        } catch (ClassNotFoundException e) {
            System.out.println("A classe "+nomeClasse+" não foi encontrada");
        }
        in.close();
    }

    /*
    *  O método imprimirHierarquia() possui uma lógica recursiva e irá ser cha-
    *  mado novamente para cada classe ou interface encontrados, parando ao se encontrar
    *  a classe Object . O parâmetro nivel representa a profundidade da classe na hi-
    *  erarquia e é utilizado para determinar a quantidade de espaços antes da classe ser
    *  impressa no console. Dessa forma, a impressão das classes ficará com um formato
    *  similar ao de uma árvore. A cada chamada recursiva, adiciona-se um no parâmetro
    *  nivel .
    *
    * */

    private static void imprimirHierarquia(Class<?> c, int nivel){
        List<Class<?>> lista = getSuperclasseEInterfaces(c);
        String recuo = "";
        for(int i=0; i<nivel; i++){
            recuo+="";
        }

        for(Class<?> clazz : lista){
            System.out.println(recuo+"|-> "+clazz.getName());
            // Vai obter toda a hierarquia até chegar na classe Object que é a classe pai de todas as classes...
            if(clazz != Object.class){
                imprimirHierarquia(clazz, nivel+1);
            }
        }
    }

    /*
    *  O método getSuperclasseEInterfaces() retorna uma lista de Class
    *  com sua superclasse e as interfaces diretamente implementadas por ela. Observe
    *  que os métodos getSuperclass() e getInterfaces() são utilizados para
    *  isso.
    * */
    private static List<Class<?>> getSuperclasseEInterfaces(Class<?> c){
        List<Class<?>> lista = new ArrayList<>();
        if(c.getSuperclass() != null)
            lista.add(c.getSuperclass());
        lista.addAll(Arrays.asList(c.getInterfaces()));
        return lista;
    }

}
