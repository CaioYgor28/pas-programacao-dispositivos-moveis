# Como gerar o APK sem Android Studio

Este projeto já está preparado para compilar no **GitHub Actions**. Você não precisa instalar Android Studio, JDK 17 ou Android SDK no computador.

## 1. Criar o repositório

1. Acesse o GitHub e entre na sua conta.
2. Clique em **New repository**.
3. Nome sugerido: `pas-programacao-dispositivos-moveis`.
4. Pode deixar o repositório **Private**.
5. Não marque README, `.gitignore` ou licença nessa tela, pois o projeto já contém esses arquivos.
6. Clique em **Create repository**.

## 2. Enviar o projeto pelo navegador

1. No repositório vazio, clique em **uploading an existing file**.
2. Abra a pasta `JardimViverCadastro` extraída deste ZIP.
3. Envie **o conteúdo da pasta**, incluindo `app`, `.github`, `gradle`, `build.gradle`, `settings.gradle` etc.
4. Confirme o commit em **Commit changes**.

> Importante: `build.gradle` e `settings.gradle` devem ficar na raiz do repositório. Não deixe o projeto dentro de duas pastas aninhadas por engano.

## 3. Rodar a compilação

O primeiro `push` para `main` ou `master` já deve iniciar a Action automaticamente.

Caso queira executar manualmente:

1. Abra a aba **Actions**.
2. Abra **Build Android APK**.
3. Clique em **Run workflow**.
4. Escolha a branch `main` (ou `master`).
5. Clique novamente em **Run workflow**.

A execução instala/configura Java 17, Android SDK 34 e Gradle 8.7 na máquina virtual do GitHub e executa:

```text
gradle --no-daemon clean assembleDebug
```

## 4. Baixar o APK

Quando a execução ficar verde:

1. Abra a execução concluída.
2. Role até **Artifacts**.
3. Clique em **JardimViver-app-debug**.
4. O GitHub baixará um ZIP com `app-debug.apk` dentro.
5. Extraia o arquivo `app-debug.apk`.

## 5. Instalar no celular Android

1. Envie `app-debug.apk` para o celular (Drive, WhatsApp Web para você mesmo, cabo USB, e-mail etc.).
2. Abra o APK no celular.
3. O Android pode pedir autorização para **Instalar apps desconhecidos** para o aplicativo usado para abrir o arquivo.
4. Autorize apenas para esse procedimento, instale e depois você pode desativar a permissão novamente.
5. Abra **Jardim Viver**.

## 6. Teste mínimo antes de gravar o vídeo

Cadastre um responsável com, por exemplo:

- Nome: Ana
- Sobrenome: Souza
- E-mail: ana.souza@email.com
- Idade: 38
- Endereço: Rua das Flores, 120

Depois teste:

1. Cadastro.
2. Persistência/listagem.
3. Edição.
4. Exclusão.
5. Fechar e abrir novamente o aplicativo para demonstrar que os dados persistem no SQLite.

## Se a Action der erro

Não altere arquivos aleatoriamente. Abra a execução vermelha, clique na etapa que falhou e copie o trecho do log a partir de `FAILURE: Build failed with an exception` (ou envie uma captura de tela). Com esse log é possível identificar exatamente o ajuste necessário.
