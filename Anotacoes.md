# Anotações 

Anotações apenas agregam informações aos elementos da classe, ou seja, **elas não fazem nada!!!**.

Uma anotação pode possuir atributos, ou seja, você pode agregar informações
aos metadados, porém não é qualquer classe que é aceita como tipo do atributo.
Porém uma anotação não pode possuir comportamento, sendo uma informação es-
tática que é adicionada a classes.


### Definição de metadados

No contexto da orientação a objetos, os metadados são informações sobre os
elementos do código. Essas informações podem ser definidas em qualquer meio,
bastando que o software ou componente as recupere e as utilize para agregar novas
informações nos elementos do código.

Existem algumas maneiras de se obter os metadados para que um determinado 
código (framework funcione):

- Convenções de código;

Exemplo: utilizando os métodos getters e setters de uma classe.

- Fontes Externas;

Exemplo: Arquivos XML.

```xml
<hibernate-mapping>
    <class name="com.casadocodigo.Usuario" table="USUARIO">
        <id column="USER_ID" name="id" type="java.lang.Long">
            <generator class="org.hibernate.id.TableHiLoGenerator">
                <param name="table">idgen</param>
                <param name="column">NEXT</param>
            </generator>
        </id>
        <property column="LOGIN" name="login" type="java.lang.String"/>
        <property column="SENHA" name="senha" type="java.lang.String"/>
        <property column="NASCIMENTO" name="dataNascimento" type="java.util.Date"/>
    </class>
</hibernate-mapping>
``` 

Uma das principais vantagens deste tipo de abordagem, é o fato de os metadados poderem ser 
atualizados em Runtime (on the fly) sem precisar reestartar a aplicação.

***Uma das desvantagens da definição de metadados em fontes externas é que os da-
dos precisam referenciar os elementos do código tornando essa configuração tediosa
e sujeita a erros, principalmente se não houver uma ferramenta de apoio.***

- Anotações

As anotações são um recurso da linguagem Java introduzidas na JDK 5 para per-
mitir a adição de metadados diretamente no código.

Elas podem ser utilizadas em conjunto com outras fontes de metadados (como por exemplo o XML).  


***Uma das desvantagens das anotações Quando elas são utilizadas, para a alteração dos metadados é
   necessário recompilar as classes, o que pode não ser viável para ajustes em tempo
   de implantação ou alteração em tempo de execução. Isso também pode dificultar a
   reutilização da classe em um contexto em que as anotações não sejam necessárias ou
   que outros metadados precisem ser configurados.***
   
***Misturando mecanismos de definição de metadados
   Como pode ser visto, cada forma de definição de metadados possui
   suas vantagens e desvantagens, e muitas vezes fica difícil equilibrar os re-
   quisitos a partir de uma das formas. Por exemplo, enquanto as anotações
   oferecem uma forma menos verbosa de definir os metadados, as fontes
   de dados externas permitem a alteração dos metadados sem a recom-
   pilação das classes. Felizmente, os componentes não precisam escolher
   apenas uma forma de definição de metadados, sendo possível combi-
   nar metadados de diferentes fontes. O capítulo ?? fala como estruturar
   o componente para permitir a combinação de metadados de diferentes
   fontes.***   ,

### Definindo até quando a anotação está disponível

Uma das principais configurações de uma anotação é até quando ela vai estar
disponível para recuperação. Dependendo do uso que será feito dela e do momento
em que essa informação será consumida, diferentes tipos de retenção podem ser ne-
cessárias. Segue uma lista com os três diferentes tipos de retenção que uma anotação
pode possuir:

- SOURCE: Uma anotação com esse tipo de retenção fica disponível apenas no
          código fonte. No momento em que a classe é compilada, a anotação não é
          transferida para o arquivo .class . Esse tipo de anotação é normalmente
          utilizada para fins de documentação e para uso de ferramentas que fazem pro-
          cessamento direto de código fonte. Um exemplo são ferramentas que fazem
          validações em tempo de compilação. 

- CLASS: Anotações com esse tipo de retenção são mantidas nos arquivos
         .class , porém não são carregadas pela máquina virtual. Nesse caso, elas
         ficam disponíveis até o momento do carregamento da classe. Esse tipo de
         anotação é utilizada por ferramentas que fazem processamento do bytecode
         da classe, podendo esse processamento ser feito de forma estática como uma
         etapa posterior a compilação, ou no momento do carregamento das classes.

- RUNTIME: Para uma anotação estar disponível para a recuperação em tempo
           de execução, ela precisa ter esse tipo de retenção. Nesse caso, a máquina vir-
           tual vai carregar essa anotação em memória e torná-la acessível através da API
           Reflection. Esse tipo de anotação é o tipo utilizado por frameworks que pre-
           cisam ter acesso as anotações em tempo de execução. Será o tipo que iremos
           utilizar com maior frequência aqui nesse livro.
           
           
Para configurar o tipo de retenção de uma anotação é preciso anotá-la com a
anotação @Retention . Essa anotação recebe como parâmetro uma enumeração
do tipo RetentionPolicy , que pode possuir os valores SOURCE , CLASS ou
RUNTIME.   

### Tipo de elementos anotados:

TYPE : qualquer definição de tipo, como classes, interfaces e enumerações.
- PACKAGE : pacotes;
- CONSTRUCTOR : construtores;
- FIELD : atributos;
- METHOD : métodos;
- PARAMETER : parâmetros de métodos e construtores;
- LOCAL_VARIABLE : variáveis locais;
- ANNOTATION_TYPE : anotações;

Para configurar o tipo de elemento que uma anotação pode anotar, é preciso
configurá-la com a anotação @Target . Essa anotação pode receber um array com
diversos tipos onde ela faz sentido ser adicionada. A listagem a seguir mostra um
exemplo de configuração, onde a anotação pode ser adicionada em um método ou
em um atributo.

```java
@Retention(RetentionPolicy.RUNTIME)    
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Metadado{}
```
