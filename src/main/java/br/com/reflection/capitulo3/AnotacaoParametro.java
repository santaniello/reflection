package br.com.reflection.capitulo3;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface Param {
    String value();
}

public class AnotacaoParametro {

    /*
    *
    *  A próxima listagem está um método chamado invocarMetodo() que
    *  recebe um Method , um objeto e um mapa de informações como parâmetro, e
    *  executa o método recuperando os seus argumentos do mapa de acordo com o
    *  nome configurado na anotação. Observe que esse método inicialmente recupera
    *  as anotações dos parâmetros pelo método getParameterAnnotations() e
    *  em seguida cria um array chamado paramValues para armazenar os valores a
    *  serem passados na chamada do método. Para cada parâmetro, o método auxiliar
    *  getNomeParâmetro() recebe a lista de anotações daquele parâmetro e busca
    *  entre elas a anotação @Param , retornando o seu valor. Em seguida, é recuperado do
    *  mapa o objeto com a chave tendo o mesmo nome configurado na anotação. Após
    *  recuperar todos os parâmetros, o método é invocado passando o array resultante.
    *
    * */

    public static Object invocarMetodo(Method m, Object obj, Map<String,Object> info) throws Exception{
        Annotation[][] paramAnnot = m.getParameterAnnotations();
        Object[] paramValues = new Object[paramAnnot.length];
        for(int i=0; i<paramValues.length; i++){
            String name = getNomeParametro(paramAnnot[i]);
            paramValues[i] = info.get(name);
        }
        return m.invoke(obj, paramValues);
    }

    public static String getNomeParametro(Annotation[] ans){
        for(Annotation a : ans){
            if(a instanceof Param){
                return ((Param)a).value();
            }
        }
        throw new RuntimeException("Anotação @Param não encontrada");
    }

    /*
    *  A listagem a seguir mostra um exemplo de uso desse método. Um método
    *  chamado metodo com dois parâmetros anotados foi criado para testarmos a invo-
    *  cação. No método main() , inicialmente é criado um mapa com diversos valores
    *  para a recuperação dos parâmetros. Em seguida, um referência para o método
    *  metodo é recuperada e o método invocarMetodo() é invocado passando esse
    *  método, a instância da classe e o mapa.
    *
    * */

    public static void main(String[] args) throws Exception {
        Map<String,Object> info = new HashMap<>();
        info.put("inteiro", 13);
        info.put("numero", 23);
        info.put("string", "OK");
        info.put("texto", "NOK");
        AnotacaoParametro ap = new AnotacaoParametro();
        Method m = ap.getClass().getMethod(
                "metodo", Integer.class, String.class);
        invocarMetodo(m, ap, info);
    }

    public void metodo(@Param("inteiro") Integer i, @Param("texto") String s){
        System.out.println("Parametro inteiro = "+i);
        System.out.println("Parametro texto = "+s);
    }
}
