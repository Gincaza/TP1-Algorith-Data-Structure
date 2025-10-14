public class Manager {
    FileReaderManager fileReader;
    SortAlgorithm sortAlgorithm;

    public Manager(String filepath, Integer quantity) {
        this.fileReader = new FileReaderManager(filepath, quantity);
        this.sortAlgorithm = new SortAlgorithm();
    }
    public static void main(String[] args) {
    }
}
