import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderManager {
    String filePath;
    Integer quantity;

    public FileReaderManager(String filePath, Integer quantity) {
        this.filePath = filePath;
        this.quantity = quantity;
    }

    public int[] readTexFile() throws IOException {
        if (filePath == null || quantity <= 0) {
            throw new IllegalArgumentException("Caminho do arquivo ou quantidade devem ser validos");
        }

        int [] numbers = new int[quantity];
        Path path = Paths.get(filePath);

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

    public void writeTextFile(List<Integer> values) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("Caminho do arquivo null");
        }

        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Valores não podem ser vazios");
        }

        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();

        for (Integer value : values) {
            lines.add(value.toString());
        }

        Files.write(path, lines);
    }

    public void writeBinaryFile(byte data[]) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("Caminho do arquivo null");
        }

        if (data == null) {
            throw new IllegalArgumentException("Dados não podem ser null");
        }

        Path path = Paths.get(filePath);
        Files.write(path, data);
    }

    public byte[] readBinaryFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("Arquivo não existe");
        }

        return Files.readAllBytes(path);
    }
}
