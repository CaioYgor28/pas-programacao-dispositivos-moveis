# Jardim Viver - Cadastro de Responsáveis

Projeto acadêmico da Ação de Aprendizagem 2 da disciplina **Programação para Dispositivos Móveis**.

## Objetivo

Implementar um aplicativo Android com formulário para cadastrar dados dos pais/responsáveis dos alunos da escola Jardim Viver. Os dados solicitados são:

- Nome
- Sobrenome
- E-mail
- Idade
- Endereço

A aplicação relaciona a interface com um DTO (`ResponsavelDTO`) e persiste os dados localmente em SQLite por meio de `DatabaseHelper`.

## Funcionalidades

- Cadastro de responsável
- Validação de campos obrigatórios
- Validação de e-mail e idade
- Persistência local SQLite
- Listagem dos registros em RecyclerView
- Edição de cadastro
- Exclusão de cadastro

## Tecnologias e configuração

- Java 17
- Android SDK 34
- minSdk 24
- AndroidX
- Material Components
- SQLite (`SQLiteOpenHelper`)

## Como executar no Android Studio

1. Instale uma versão recente do Android Studio com JDK 17.
2. Abra o Android Studio e selecione **Open**.
3. Selecione a pasta `JardimViverCadastro`.
4. Aguarde a sincronização das dependências do Gradle.
5. Caso o Android Studio informe que não encontrou o Gradle Wrapper, selecione a opção para usar/gerar um wrapper compatível com Gradle 8.7. O arquivo `gradle/wrapper/gradle-wrapper.properties` já contém a versão prevista para o projeto.
6. Confirme que o Android SDK 34 está instalado pelo SDK Manager.
7. Crie um emulador Android (API 24 ou superior) ou conecte um aparelho com depuração USB.
8. Execute o módulo `app` pelo botão **Run**.

> Observação: o arquivo `local.properties` não acompanha o projeto porque contém o caminho local do Android SDK de cada computador. O Android Studio normalmente cria esse arquivo automaticamente.

## Principais arquivos exigidos pela atividade

- `app/src/main/java/br/com/techz/jardimviver/model/ResponsavelDTO.java`: classe DTO com os atributos do formulário.
- `app/src/main/java/br/com/techz/jardimviver/MainActivity.java`: contém o método `onCreate()` e a inicialização dos campos da tela.
- `app/src/main/java/br/com/techz/jardimviver/data/DatabaseHelper.java`: persistência SQLite.
- `app/src/main/res/layout/activity_main.xml`: desenho da interface principal.
- `app/src/main/res/layout/item_responsavel.xml`: item da listagem.

## Dados sugeridos para demonstração

Nome: Ana
Sobrenome: Souza
E-mail: ana.souza@email.com
Idade: 38
Endereço: Rua das Flores, 120

## Compilar sem Android Studio (GitHub Actions)

Para computadores sem Android Studio/Android SDK, o projeto inclui o workflow `.github/workflows/build-apk.yml`.

Consulte `COMO_GERAR_APK_NO_GITHUB.md` para o passo a passo. O workflow configura Java 17, Android SDK 34 e Gradle 8.7 e disponibiliza `app-debug.apk` como artefato da execução.
