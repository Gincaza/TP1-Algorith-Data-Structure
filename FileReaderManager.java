import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderManager {
    private String textFilePath;
    private String binaryFilePath;
    private Integer quantity;

    private void setFilePath(String baseFilePath) {
        
        if (baseFilePath == null) {
            throw new IllegalArgumentException("FilePath não pode ser null");
        }

        this.textFilePath = baseFilePath + ".txt";
        this.binaryFilePath = baseFilePath + ".bin";

        Path textPath = Paths.get(textFilePath);
        Path binaryPath = Paths.get(binaryFilePath);
        
        if (!Files.exists(textPath) && !Files.exists(binaryPath)) {
            createBothFiles();
        }
    }

    private void setQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantidade invalida");
        }

        this.quantity = quantity;
    }

    public FileReaderManager(String filePath, Integer quantity) {
        setQuantity(quantity);
        setFilePath(filePath);
    }

    // Getters
    public String getTextFilePath() {
        return textFilePath;
    }

    public String getBinaryFilePath() {
        return binaryFilePath;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public int[] readTextFile() throws IOException {
        if (textFilePath == null || quantity <= 0) {
            throw new IllegalArgumentException("Caminho do arquivo ou quantidade devem ser validos");
        }

        int [] numbers = new int[quantity];
        Path path = Paths.get(textFilePath);

        if (!Files.exists(path)) {
            throw new IOException("Arquivo não encontrado");
        }

        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < Math.min(lines.size(), quantity); i++) {
            try {
                numbers[i] = Integer.parseInt(lines.get(i).trim());
            } catch (NumberFormatException e) {
                throw new IOException("Erro ao converter para número");
            }
        }

        return numbers;
    }

    public void writeTextFile(int[] values) throws IOException {
        if (textFilePath == null) {
            throw new IllegalArgumentException("Caminho do arquivo null");
        }

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Valores não podem ser vazios");
        }

        Path path = Paths.get(textFilePath);
        List<String> lines = new ArrayList<>();

        for (Integer value : values) {
            lines.add(value.toString());
        }

        Files.write(path, lines);
    }

    public void writeBinaryFile(byte data[]) throws IOException {
        if (binaryFilePath == null) {
            throw new IllegalArgumentException("Caminho do arquivo null");
        }

        if (data == null) {
            throw new IllegalArgumentException("Dados não podem ser null");
        }

        Path path = Paths.get(binaryFilePath);
        Files.write(path, data);
    }

    public byte[] readBinaryFile() throws IOException {
        if (binaryFilePath == null) {
            throw new IllegalArgumentException("Caminho do arquivo não pode ser null");
        }
        
        Path path = Paths.get(binaryFilePath);
        if (!Files.exists(path)) {
            throw new IOException("Arquivo não existe");
        }

        return Files.readAllBytes(path);
    }

    private void createBothFiles() {
        int[] numbers = intGenerator();
        try {
            writeTextFile(numbers);
            writeBinaryFile(DataConverter.intsToBytes(numbers));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar os arquivos", e);
        }
    }

    private int[] intGenerator() {
        int[] values = new int[quantity];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < quantity; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }
}
