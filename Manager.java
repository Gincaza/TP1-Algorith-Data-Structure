public class Manager {
    FileReaderManager fileReader;
    SortAlgorithm sortAlgorithm;
    DataConverter dataConverter;

    public Manager(String filepath, Integer quantity) {
        this.fileReader = new FileReaderManager(filepath, quantity);
        this.sortAlgorithm = new SortAlgorithm();
        this.dataConverter = new DataConverter();
    }
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Uso: java Manager <caminho_arquivo> <quantidade>");
            System.out.println("Exemplo: java Manager dados.txt 1000");
            return;
        }
        
        try {
            String caminhoArquivo = args[0];
            Integer quantidade = Integer.parseInt(args[1]);
            
            // Criar o manager com os parâmetros
            Manager manager = new Manager(caminhoArquivo, quantidade);
            
        } catch (NumberFormatException e) {
            System.err.println("Erro: Quantidade deve ser um número válido");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
