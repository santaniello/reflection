package br.com.reflection.capitulo2;

import java.lang.reflect.Method;
import java.util.Scanner;

/*
* Na listagem a seguir, está apresentado o corpo principal do programa que exe-
  cuta o método de acordo com as entradas do usuário. Inicialmente ele solicita ao
  usuário o nome da classe e em seguida o nome do método. O método será pro-
  curado utilizando o método acharMetodoPeloNome() apresentado na listagem
  anterior.
  A partir do método recuperado, os tipos e a quantidade dos parâmetros são re-
  cuperados para serem solicitados ao usuário. Para cada parâmetro, é solicitado para
  o usuário que entre com um valor. Esse valor é lido como uma String e é utilizado
  para criação do objeto que será o valor do parâmetro, através da invocação de um
  construtor que recebe uma String como argumento. Sendo assim, se o tipo do pa-
  râmetro for Integer , será invocado seu construtor passando a String lida como
  parâmetro.
  Depois que todos os parâmetros forem lidos, o método é invocado utilizando
  método invoke() e seu resultado é impresso no console.
**/

public class ExecutaMetodo {

    private static final String PACKAGE = "br.com.reflection.capitulo2.";

    public static void main(String[] args) throws Exception{
        System.out.println(
                "Entre com o nome da classe "
                        + "com método que deseja executar:");
        Scanner in = new Scanner(System.in);
        String nomeClasse = in.nextLine();
        Class<?> c = Class.forName(PACKAGE+nomeClasse);
        System.out.println("Entre com o nome do método:");
        String nomeMetodo = in.nextLine();
        Method m = acharMetodoPeloNome(c, nomeMetodo);
        System.out.println(m.getParameterTypes().length);
        // monta um array de objetos baseado na quantidade de parâmetros do método...
        Object[] params = new Object[m.getParameterTypes().length];
        for(int i=0; i<params.length; i++){
            // pega o parametro na posição i...
            Class<?> paramType = m.getParameterTypes()[i];
            System.out.println("Parametro "+(i+1)+
                    " ("+paramType.getName()+")");
            // lê o valor digitado pelo usuário...
            String valor = in.nextLine();

            /* Instancia o objeto antes de joga-lo em um array de parâmtros...
               Se o objeto for um Integer por exemplo, uma string será passado para que
               o valor seja convertido para um inteiro
               Exemplo: new Integer("1236");
            */
            params[i] = paramType.
                    getConstructor(String.class).newInstance(valor);
        }

        // Invocando o método ...
        Object retorno = m.invoke(c.newInstance(), params);
        System.out.println("O método retornou: " + retorno);
        in.close();
    }

    private static Method acharMetodoPeloNome(Class<?> c, String nome)
            throws Exception{
        for(Method m : c.getMethods()){
            if(m.getName().equals(nome)){
                return m;
            }
        }
        throw new Exception("Método "+nome+" não encontrado");
    }
}
