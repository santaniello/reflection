package br.com.reflection.generatemap;

import java.util.Map;

public class TestMapProperties {
    public static void main(String[] args) {
        Produto p = new Produto("Design Patterns","LIVRO",59.90,
               "Publicado pela Casa do Codigo");
        Map<String,Object> props = GeneratorMap.generateMap(p);
        for(String prop : props.keySet()){
            System.out.println(prop+" = "+props.get(prop));
        }
    }
}
