package br.com.reflection.capitulo2;

import java.util.List;

public class ValidacaoException extends Exception {
    private List<Exception> erros;
    public ValidacaoException(List<Exception> erros) {
this.erros = erros;
}
    public List<Exception> getErros() {
return erros;
}
}