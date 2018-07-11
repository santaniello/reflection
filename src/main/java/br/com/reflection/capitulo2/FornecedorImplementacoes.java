package br.com.reflection.capitulo2;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
  Classe que lê um arquivo de propriedades (o qual em cada linha possui
  propriedade = valor), com interfaces e suas respectivas implementações. Nesse
  arquivo, as propriedades seriam os nomes das interfaces e o valor o nome de
  sua respectiva implementação que deve ser utilizada na aplicação. A classe
  FornecedorImplementacoes recebe o nome do arquivo no construtor e utiliza
  a classe Properties para fazer a leitura do arquivo. Para cada propriedade,
  o Class.forName() é utilizado para obter instância de Class da interface e
  sua respectiva implementação, adicionando ambas em um mapa para posterior
  recuperação.
*/

public class FornecedorImplementacoes {

    private Map<Class<?>, Class<?>> implementacoes = new HashMap<>();
    private static final String PACKAGE = "br.com.reflection.capitulo2.";

    public FornecedorImplementacoes(String nomeArquivo) throws Exception{
        Properties p = new Properties();
        p.load(new FileInputStream(nomeArquivo));
        for(Object interf : p.keySet()){
            Class<?> interfType = Class.forName(PACKAGE+interf.toString());
            Class<?> implType = Class.forName(PACKAGE+p.get(interf).toString().trim());
            if(!isAbstracaoEImplementacao(interfType, implType)){
                throw new Exception("Erro na configuração do arquivo " +
                        nomeArquivo + " : " + interfType.getName() +
                        " não é abstração de " + implType.getName());
            }
            implementacoes.put(interfType, implType);
        }
    }

    private  Class<?> getImplementacao(Class<?> interf){
        return implementacoes.get(interf);
    }


    /*
    * O método isInterfaceOuAbstract() verifica se o Class passado como
    * parâmetro é uma interface ou uma classe abstrata.
    * Observe que a classe auxiliar
    *  Modifiers foi utilizada para verificar se o modificador abstract está presente.
    * */
    private boolean isInterfaceOrAbstract(Class<?> c){
        return c.isInterface() || Modifier.isAbstract(c.getModifiers());
    }

    /*
    * Método isAbstracaoEImplementacao() que verifica se
    * a primeira classe é uma abstração, se a segunda classe é uma implementação e se a
    * implementação é do tipo da abstração.
    * */
    private boolean isAbstracaoEImplementacao
            (Class<?> interf, Class<?> impl){
        return isInterfaceOrAbstract(interf) && !isInterfaceOrAbstract(impl)
                && interf.isAssignableFrom(impl);
    }

    /*
    * Método que O objetivo é que a partir dos parâmetros passados para a criação do objeto,
    * seja encontrado um construtor que possa receber aqueles parâmetros. A listagem a seguir
    * apresenta um método auxiliar que será utilizado para achar um construtor adequado aos
    * parâmetros passados.
    *
    * OBS: Object... é o que chamamos de varargs que é um açucar sintático para
    * a seguinte expressão: new Object[]
    *
    * Outro exemplo em que poderiamos usar o varargs é no String[] args do método main
    * utilizando a seguinte expressão: String...
    *
    * */
    private Constructor<?> acharConstrutor(Class<?> c, Object... objs)
            throws Exception{
        for(Constructor<?> constr : c.getConstructors()){
            Class<?>[] params = constr.getParameterTypes();
            if(params.length == objs.length){
                boolean erro = false;
                for(int i=0; i<objs.length && !erro; i++){
                    if(!params[i].isInstance(objs[i]))
                        erro = true;
                }
                if(!erro)
                    return constr;
            }
        }
        throw new Exception("Construtor não encontrado");
    }

   /*
    *
       Método que efetivamente cria o objeto. Esse método inicialmente
    *  utiliza o método getImplementacao() para recuperar a implementação da
    *  interface ou classe abstrata passada como parâmetro. Em seguida, o método criado
    *  na listagem anterior é utilizado para recuperar o construtor, o qual é utilizado para
    *  a criação da instância através do método newInstance() .
    * */
    public Object criarInstancia(Class<?> interf, Object... objs)
            throws Exception{
        Class<?> impl = getImplementacao(interf);
        Constructor<?> constr = acharConstrutor(impl, objs);
        return constr.newInstance(objs);
    }


    public static void main(String[] args) throws Exception {
        try {
            FornecedorImplementacoes fornecedorImplementacoes = new FornecedorImplementacoes("recuperandoClassPorString.properties");
            // Instanciamos uma implementação da classe Fornecedor passando os parâmetros nome e idade...
            Object o = fornecedorImplementacoes.criarInstancia(Fornecedor.class,"Felipe",26);
            FornecedorImpl f = (FornecedorImpl)o;
            System.out.println(f.getNome());
            System.out.println(f.getIdade());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
