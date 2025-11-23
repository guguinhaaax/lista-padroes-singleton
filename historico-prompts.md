# Histórico de prompts

*A IA utilizada para a realização dessa atividade foi o Google Gemini na versão 3 Pro*

**Prompt**:
    considerando a questão abaixo,  gere um passo a passo para suaa solução, utilizando a linguagem java para a resposta   

        Vários tipos de software mantêm arquivos de configuração/propriedades
        centralizados por conta do benefício de consistência e desempenho. Isso evita que
        vários usuários acessem e leiam repetidamente o arquivo. O software cria uma única
        instância do arquivo de configuração que pode ser acessada por várias chamadas
        simultaneamente, pois fornecerá dados de configuração estáticos carregados em
        objetos na memória. O software apenas lê o arquivo de configuração na primeira vez
        e, a partir da segunda chamada, os aplicativos clientes leem os dados dos objetos na
        memória.
        Dado o exemplo de arquivo de configuração de um jogo abaixo, usando o Padrão
        Singleton, escreva um programa que lê o arquivo, o armazena em memória, e imprime
        todos os dados do arquivo de configuração. Você deverá armazenar os dados do
        arquivo numa classe que modela/representa o arquivo de configuração a ser lido.
        Note que outros componentes do software que usam o arquivo de configuração
        podem querer acessar individualmente os campos do arquivo de configuração.

        AppId=1649240
        UserName=elamigos
        Language=brazilian
        Offline=0
        AutoDLC=0
        BuildId=10547147
        DLCName=Returnal Pre-Purchase Entitlement
        UpdateDB=1
        Signature=FitGirl
        WindowInfo=LAAAAAAAAAAIP50AAACIAAAATQMAAJACAAA=
        LVWindowInfo=AwAAADEBAAByAAAApQAAAA==
        ApplicationPath=../../../Returnal.exe
        WorkingDirectory=
        WaitForExit=1
        NoOperation=0

**Resposta**:
Aqui está uma solução passo a passo utilizando a linguagem Java. O foco é aplicar o **Padrão Singleton** para garantir que o arquivo seja lido apenas uma vez e que os dados fiquem centralizados na memória.

### Passo 1: Criar o Arquivo de Configuração

Primeiro, precisamos do arquivo físico que o programa irá ler. Crie um arquivo chamado `game.properties` na raiz do seu projeto (ou na mesma pasta do código fonte, dependendo da sua IDE) e cole o conteúdo fornecido:

```text
AppId=1649240
UserName=elamigos
Language=brazilian
Offline=0
AutoDLC=0
BuildId=10547147
DLCName=Returnal Pre-Purchase Entitlement
UpdateDB=1
Signature=FitGirl
WindowInfo=LAAAAAAAAAAIP50AAACIAAAATQMAAJACAAA=
LVWindowInfo=AwAAADEBAAByAAAApQAAAA==
ApplicationPath=../../../Returnal.exe
WorkingDirectory=
WaitForExit=1
NoOperation=0
```

-----

### Passo 2: Implementar a Classe Singleton

Esta classe será responsável por modelar os dados e garantir a instância única. Utilizaremos a classe `java.util.Properties` do Java, que é nativa e otimizada para ler arquivos no formato `chave=valor`.

**Características principais desta classe:**

  * **Construtor Privado:** Impede que outras classes façam `new GameConfig()`.
  * **Atributo Estático (`instance`):** Guarda a única instância da classe.
  * **Método `getInstance()`:** Verifica se a instância já existe. Se não, cria e lê o arquivo. Se já existe, apenas retorna a que está na memória.

<!-- end list -->

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class GameConfig {

    // 1. A única instância da classe (static)
    private static GameConfig instance;

    // Armazenamento dos dados em memória (chave/valor)
    private Properties properties;

    // 2. Construtor Privado: Só é executado uma vez internamente
    private GameConfig() {
        properties = new Properties();
        try {
            // Tenta ler o arquivo. Ajuste o caminho se necessário.
            FileInputStream file = new FileInputStream("game.properties");
            properties.load(file);
            file.close();
            System.out.println(">> Arquivo carregado do disco para a memória.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
        }
    }

    // 3. Ponto de acesso global (Lazy Initialization)
    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    // --- Métodos de Acesso Individual (Requisito do exercício) ---

    // Exemplo de getter específico (acesso tipado)
    public String getAppId() {
        return properties.getProperty("AppId");
    }

    public String getUserName() {
        return properties.getProperty("UserName");
    }

    // Getter genérico para qualquer campo
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Método para imprimir todos os dados (Requisito do exercício)
    public void printAllData() {
        System.out.println("--- Configurações Atuais do Jogo ---");
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + " = " + properties.getProperty(key));
        }
        System.out.println("------------------------------------");
    }
}
```

-----

### Passo 3: Criar a Classe Principal (Client)

Esta classe simula o software do jogo acessando a configuração. Vamos chamar o `getInstance()` duas vezes para provar que o arquivo só é lido na primeira vez.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o Jogo...");

        // 1ª Chamada: O arquivo será lido do disco
        GameConfig config1 = GameConfig.getInstance();
        
        // Exibindo todos os dados como pedido
        config1.printAllData();

        // Acessando campos individuais
        System.out.println("Acessando campo individualmente:");
        System.out.println("Usuário logado: " + config1.getUserName());
        System.out.println("ID da Build: " + config1.getProperty("BuildId"));

        System.out.println("\n--- Simulando acesso de outro componente ---");

        // 2ª Chamada: O arquivo NÃO é lido novamente (usa memória)
        GameConfig config2 = GameConfig.getInstance();
        
        // Prova de que é o mesmo objeto na memória
        if (config1 == config2) {
            System.out.println("Sucesso: Ambas as variáveis apontam para a mesma instância Singleton.");
        }
    }
}
```

-----

### Resumo da Lógica

1.  **Centralização:** A classe `GameConfig` encapsula toda a complexidade de leitura de arquivo.
2.  **Performance:** Note que no código do `Main`, a mensagem `">> Arquivo carregado..."` aparecerá apenas uma vez, mesmo que você chame `getInstance()` mil vezes.
3.  **Acesso Individual:** Criamos métodos como `getUserName()` para que outras partes do código não precisem saber a string exata da chave "UserName", evitando erros de digitação (Magic Strings).


**Avaliação do Prompt**: A solução gerada pelo prompt foi bastante satisfatória e resolveu todas as exigências pedidas pela questão.