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
            System.out.println("Use: java Manager <file_path> <quantity_of_integers>");
            System.out.println("Example: java Manager data.txt 1000");
            return;
        }
        
        try {
            String caminhoArquivo = args[0];
            Integer quantidade = Integer.parseInt(args[1]);
            
            Manager manager = new Manager(caminhoArquivo, quantidade);
            
        } catch (NumberFormatException e) {
            System.err.println("Error: Quantity must be a valid number");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
