package br.com.reflection.capitulo1.testperformance;

import br.com.reflection.capitulo1.generatemap.Ignorar;
import br.com.reflection.capitulo1.generatemap.NomePropriedade;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GeradorMapaPerformance {
    private Map<String, Method> propriedades = new HashMap<>();
    private Class<?> classe;

    public GeradorMapaPerformance(Class<?> classe) {
        this.classe = classe;
        for (Method method : classe.getMethods()) {
            if (isGetter(method)) {
                String propriedade = null;
                if (method.isAnnotationPresent(NomePropriedade.class)) {
                    propriedade = method.getAnnotation(NomePropriedade.class).value();
                } else {
                    propriedade = fromGetterToPropertie(method.getName());
                }
                propriedades.put(propriedade, method);
            }
        }
    }

    public Map<String, Object> gerarMapa(Object o) {
        if (!classe.isInstance(o)) {
            throw new RuntimeException("O objeto não é da classe"
                    + classe.getName());
        }
        Map<String, Object> mapa = new HashMap<>();
        for (String propriedade : propriedades.keySet()) {
            try {
                Method m = propriedades.get(propriedade);
                Object valor = m.invoke(o);
                mapa.put(propriedade, valor);
            } catch (Exception e) {
                throw new RuntimeException("Problema ao gerar o mapa", e);
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
    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get") &&
               method.getReturnType() != void.class &&
               method.getParameterTypes().length == 0 &&
               // verifica se a anotação @Ignorar não está presente
               !method.isAnnotationPresent(Ignorar.class);
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
