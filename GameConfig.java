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