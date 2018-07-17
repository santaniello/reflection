package br.com.reflection.capitulo2;

public class ExemploClasse {
    public String publico;
    private String privado;
    public static String estatico;

    public ExemploClasse(){}

    public ExemploClasse(String publico, String privado, String estatico){
        this.publico = publico;
        this.privado = privado;
        this.estatico = estatico;
    }

    public void teste(){
        System.out.println("Método executado com sucesso !");
    }

    public void testeComParametro(String parametro1,Integer parametro2){
        System.out.println("Método executado com sucesso !: "+ parametro1 + " - " + parametro2);
    }
}
