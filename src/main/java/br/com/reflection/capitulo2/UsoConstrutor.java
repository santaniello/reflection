package br.com.reflection.capitulo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
*  O código abaixo mostra o exemplo do uso de um construtor. A classe
*  UsoConstrutor possui um construtor que recebe uma String como argumento.
*  O método getConstructor() é utilizado para recuperar o construtor e o
*  método newInstance() para a criação do objeto. Observe que no tratamento de
*  InvocationTargetException a exceção original é recuperada e impressa no
*  console.
* */
public class UsoConstrutor {
    public UsoConstrutor(String s){
        System.out.println("Construtor invocado com o parâmetro: "+s);
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        Class<UsoConstrutor> c = UsoConstrutor.class;
        Constructor<UsoConstrutor> constr = c.getConstructor(String.class);
        try {
            UsoConstrutor obj = constr.newInstance("teste");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
