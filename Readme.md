# Reflection Api

A classe principal que é o ponto de partida para obtermos informações sobre os elementos de um programa 
é a classe java.lang.Class que representa justamente uma classe. Sendo assim, o primeiro passo para começar
a trabalhar com reflexão é obtermos a instância de Class da classe que queremos trabalhar com ela.
 
 
**A instância da classe Class possui todas as informações de uma classe. Essa instância possui as metainformações, 
não do objeto, mas da classe.**

![REFLECTION](DOCS/reflection.png)


Apesar de tipos primitivos em Java não serem considerados classes, é possível
obter uma representação de Class para eles. Para isso, a referência estática des-
ses tipos pode ser utilizada, como por exemplo int.class e char.class . Até
mesmo o void possui uma representação de Class, sendo obtido através da expressão void.class .

### Quando criar objetos utilizando reflexão?

A criação de objetos utilizado reflexão é indicada quando não se co-
nhece em tempo de compilação a classe que vai ser instanciada. No caso
de haverem diversas implementações para uma classe, mas todas serem
conhecidas, um padrão de criação, como Factory Method ou Builder,
pode ser utilizado para determinar a classe que será criada. A criação de
objetos através de reflexão é indicada quando se deseja que novas clas-
ses possam ser criadas e configuradas como plugins de um software ou
framework existente.