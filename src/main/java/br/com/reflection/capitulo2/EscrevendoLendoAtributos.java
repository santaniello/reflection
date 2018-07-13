package br.com.reflection.capitulo2;

import java.lang.reflect.Field;

public class EscrevendoLendoAtributos {
    public static void escreverLerAtributo(Field field, Object instancia) {
        try {
            field.set(instancia, field.getName());
            Object valor = field.get(instancia);
            System.out.println("Escrito e lido o atributo = " + valor);
        } catch (IllegalArgumentException e) {
            System.out.println("Problemas ao acessar atributo "
                    + field.getName() + ": " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("Problemas de acesso no atributo "
                    + field.getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        ExemploClasse instancia = new ExemploClasse();
        Class<?> clazz = instancia.getClass();
        escreverLerAtributo(clazz.getField("publico"), instancia);

        /*
         *  O atributo privado não conseguirá ser acessado e a
         *  exceção IllegalAccessException será lançada. Caso esse código estivesse na
         *  própria classe ExemploClasse , esse atributo estaria acessível e o erro não aconte-
         *  ceria. É importante ressaltar que por mais que os atributos privados estejam acessí-
         *  veis para ser recuperados via reflexão, nem sempre eles poderão ser acessados.

         * escreverLerAtributo(clazz.getDeclaredField("privado"), instancia);
         **/
        escreverLerAtributo(clazz.getField("estatico"), null);
    }
}
