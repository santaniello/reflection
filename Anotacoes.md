# Anotações 

Anotações apenas agregam informações aos elementos da classe, ou seja, **elas não fazem nada!!!**.

Uma anotação pode possuir atributos, ou seja, você pode agregar informações
aos metadados, porém não é qualquer classe que é aceita como tipo do atributo.
Porém uma anotação não pode possuir comportamento, sendo uma informação es-
tática que é adicionada a classes.

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

