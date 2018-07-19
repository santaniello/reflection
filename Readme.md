# Reflection Api

A classe principal que é o ponto de partida para obtermos informações sobre os elementos de um programa 
é a classe java.lang.Class que representa justamente uma classe. Sendo assim, o primeiro passo para começar
a trabalhar com reflexão é obtermos a instância de Class da classe que queremos trabalhar com ela.
 
 
**A instância da classe Class possui todas as informações de uma classe. Essa instância possui as metainformações, 
não do objeto, mas da classe.**

![REFLECTION](DOCS/reflection.png)

### Formas de obter instâncias de Class

- Referências Estáticas;      
- Recuperando a classe de um objeto;
- Uma String com o nome da classe;

Exemplo usando Referências Estáticas:

```java    
    Class<String> classe = String.class;
    System.out.println(classe.getName());
```

Exemplo recuperando a classe de um objeto:

```java    
   Number object = new Integer(100);
   Class<? extends Number> c = object.getClass();
```

Exemplo usando String com o nome da classe:

```java    
   Class<?> classe = Class.forName("br.com.teste.Classe");
```
    
***Dica: Apesar de tipos primitivos em Java não serem considerados classes, é possível
obter uma representação de Class para eles. Para isso, a referência estática des-
ses tipos pode ser utilizada, como por exemplo int.class e char.class . Até
mesmo o void possui uma representação de Class, sendo obtido através da expressão void.class.***


### Quando criar objetos utilizando reflexão?

A criação de objetos utilizado reflexão é indicada quando não se co-
nhece em tempo de compilação a classe que vai ser instanciada. No caso
de haverem diversas implementações para uma classe, mas todas serem
conhecidas, um padrão de criação, como Factory Method ou Builder,
pode ser utilizado para determinar a classe que será criada. A criação de
objetos através de reflexão é indicada quando se deseja que novas clas-
ses possam ser criadas e configuradas como plugins de um software ou
framework existente.

-------------------------------------------------------------------------------------------

### Cuidado com os tipos primitivos!

Os tipos primitivos são uma grande pedra no sapado de quem traba-
lha com reflexão. Apesar do seu tipo ser representado pela classe Class ,
muitas vezes é preciso fazer a distinção dos tipos primitivos e tratá-los
como casos especiais. O exemplo da busca de construtores é um caso
em que os tipos primitivos precisariam ser tratados separadamente, tro-
cando seu tipo pelo de uma classe wrapper ou utilizando um condicional
para cada tipo primitivo. Sendo assim, não se incomode ao ter que criar
uma séria de condicionais para tratar de forma especial cada um dos ti-
pos primitivos, pois nos frameworks que desenvolvi precisei fazer isso
algumas vezes.

------------------------------------------------------------------------------------

### Quando executar métodos por reflexão ?

A orientação a objetos provê meios de você invocar métodos em um
objeto que você não conhece previamente. Isso pode ser feito definindo
uma abstração, como uma interface ou uma classe, que possua essa mé-
todo e utilizando o polimorfismo para invocar esse método em qualquer
objeto que obedeça a essa abstração. Nesses casos, quando é possível ter
uma abstração que representa o método que deve ser invocado, a reflexão
não precisa ser utilizada. Por outro lado, se você precisa lidar com clas-
ses que possuem métodos diferentes e não faz sentido compartilharem
uma mesma abstração, essa é a deixa para a invocação desses métodos
por reflexão. Um bom exemplo desse caso são os Java Beans, que pos-
suem diversos métodos getter e setter diferentes e não é possível criar
uma interface comum que capture essa característica.

------------------------------------------------------------------------------------

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

