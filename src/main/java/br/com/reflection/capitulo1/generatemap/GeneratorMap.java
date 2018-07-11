package br.com.reflection.capitulo1.generatemap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GeneratorMap {

    public static Map<String, Object> generateMap(Object o) {
        Class<?> classe = o.getClass();
        Map<String, Object> mapa = new HashMap<>();
        for (Method m : classe.getMethods()) {
            try {
                if (isGetter(m)) {
                    String propriedade = null;
                    if(m.isAnnotationPresent(NomePropriedade.class)){
                        propriedade = m.getAnnotation(NomePropriedade.class).value();
                    }else{
                        propriedade = fromGetterToPropertie(m.getName());
                    }
                    Object valor = m.invoke(o);
                    mapa.put(propriedade, valor);
                }
            } catch (Exception e) {
                throw new RuntimeException(
                        "Problema ao gerar o mapa", e);
            }
        }
        return mapa;
    }

    /*
    *  O método isGetter() acessa as informações do método verificando por suas
    *  informações se se trata de um método getter. As informações consideradas para isso
    *  são: se nome do método se inicia com “get”, se o retorno é diferente de void e se ele
    *  não possui parâmetros. Caso o método seja identificado como getter, ele é invocado
    *  a partir do método invoke() e seu valor recuperado para ser inserido no mapa.
    * */
    private static boolean isGetter(Method m) {
        return m.getName().startsWith("get") &&
                m.getReturnType() != void.class &&
                m.getParameterTypes().length == 0 &&
                // verifica se a anotação @Ignorar não está presente
               !m.isAnnotationPresent(Ignorar.class);
    }

    /*
    * Observe que o nome do método é transformado no nome da propriedade pelo
    * método fromGetterToPropertie() para adição no mapa.
    * */
    private static String fromGetterToPropertie(String nomeGetter){
        StringBuffer retorno = new StringBuffer();
        retorno.append(nomeGetter.substring(3, 4).toLowerCase());
        retorno.append(nomeGetter.substring(4));
        return retorno.toString();
    }

}
