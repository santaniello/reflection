package br.com.reflection.capitulo1.generatemap;

public class Telefone {
    private String codigoPais;
    private String operadora;

    @NomePropriedade("codigoInternacional")
    public String getCodigoPais() {
        return this.codigoPais;
    }
    @Ignorar
    public String getOperadora() {
        return operadora;
    }
}