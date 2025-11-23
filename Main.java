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