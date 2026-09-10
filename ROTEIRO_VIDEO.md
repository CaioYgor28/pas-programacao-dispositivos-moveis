# Roteiro de demonstração - Ação 2

Duração sugerida: 3 a 5 minutos.

## 1. Apresentação (20 a 30 segundos)

“Este é o aplicativo desenvolvido para a escola Jardim Viver. O objetivo é cadastrar os dados dos pais ou responsáveis dos alunos, contendo nome, sobrenome, e-mail, idade e endereço.”

## 2. Mostrar rapidamente o código (1 a 2 minutos)

Abra `ResponsavelDTO.java` e explique:

“Esta é a classe DTO responsável por representar os dados digitados na tela antes da persistência.”

Abra `MainActivity.java`, no método `onCreate()`, e explique:

“No método onCreate são associados os componentes da interface aos atributos utilizados pela Activity por meio de findViewById. Também são inicializados o banco, a RecyclerView e os eventos dos botões.”

Abra `DatabaseHelper.java` e explique:

“A persistência foi implementada com SQLite. A tabela possui os campos id, nome, sobrenome, e-mail, idade e endereço.”

## 3. Demonstrar o aplicativo (2 minutos)

1. Abra o aplicativo no emulador.
2. Mostre o formulário vazio.
3. Cadastre:
   - Ana
   - Souza
   - ana.souza@email.com
   - 38
   - Rua das Flores, 120
4. Clique em **Salvar cadastro**.
5. Mostre o registro aparecendo na listagem.
6. Clique em **Editar**, altere o endereço e salve novamente.
7. Feche e reabra o aplicativo para mostrar que o registro continua armazenado.
8. Opcionalmente, clique em **Excluir** para demonstrar a última operação do CRUD.

## 4. Encerramento (15 segundos)

“Com isso, a aplicação relaciona o front-end com a classe DTO e a camada de persistência, mantendo os dados localmente em banco SQLite e permitindo cadastro, consulta, edição e exclusão.”
