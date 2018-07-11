package br.com.reflection.capitulo1.testperformance;

public class InvocadorNormal implements InvocadorMetodo{

    @Override
    public void invocarMetodo(int vezes) {
        ClasseTeste ct = new ClasseTeste();
        for(int i=0; i<vezes; i++){
            ct.metodoVazio();
        }
    }
}
