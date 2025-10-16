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
            throw new IllegalArgumentException(ErrorMessages.FILE_PATH_NULL);
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
            throw new IllegalArgumentException(ErrorMessages.QUANTITY_INVALID);
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
            throw new IllegalArgumentException(ErrorMessages.QUANTITY_PATH_INVALID);
        }

        int [] numbers = new int[quantity];
        Path path = Paths.get(textFilePath);

        if (!Files.exists(path)) {
            throw new IOException(ErrorMessages.FILE_NOT_FOUND);
        }

        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < Math.min(lines.size(), quantity); i++) {
            try {
                numbers[i] = Integer.parseInt(lines.get(i).trim());
            } catch (NumberFormatException e) {
                throw new IOException(ErrorMessages.CONVERSION_ERROR);
            }
        }

        return numbers;
    }

    public void writeTextToFile(List<String> lines, String filePath) throws IOException {
        if (lines == null) {
            throw new IllegalArgumentException(ErrorMessages.LINES_NULL);
        }
        if (filePath == null) {
            throw new IllegalArgumentException(ErrorMessages.FILE_PATH_NULL);
        }
        
        Path path = Paths.get(filePath);
        Files.write(path, lines);
    }

    public void writeBinaryToFile(byte[] data, String filePath) throws IOException {
        if (data == null) {
            throw new IllegalArgumentException(ErrorMessages.DATA_NULL);
        }
        if (filePath == null) {
            throw new IllegalArgumentException(ErrorMessages.FILE_PATH_NULL);
        }
        
        Path path = Paths.get(filePath);
        Files.write(path, data);
    }

    private void initTextFile(int[] values) throws IOException {

        List<String> lines = new ArrayList<>();
        for (int value : values) {
            lines.add(String.valueOf(value));
        }
        writeTextToFile(lines, this.textFilePath);
    }

    private void initByteFile(byte data[]) throws IOException {
        if (binaryFilePath == null) {
            throw new IllegalArgumentException(ErrorMessages.FILE_PATH_NULL);
        }

        if (data == null) {
            throw new IllegalArgumentException(ErrorMessages.DATA_NULL);
        }

        writeBinaryToFile(data, this.binaryFilePath);
    }

    public byte[] readBinaryFile() throws IOException {
        if (binaryFilePath == null) {
            throw new IllegalArgumentException(ErrorMessages.FILE_PATH_NULL);
        }
        
        Path path = Paths.get(binaryFilePath);
        if (!Files.exists(path)) {
            throw new IOException(ErrorMessages.FILE_NOT_EXISTS);
        }

        return Files.readAllBytes(path);
    }

    private void createBothFiles() {
        int[] numbers = intGenerator();
        try {
            initTextFile(numbers);
            initByteFile(DataConverter.intsToBytes(numbers));
            System.out.println(String.format(ErrorMessages.FILES_CREATED, textFilePath, binaryFilePath));
        } catch (IOException e) {
            throw new RuntimeException(ErrorMessages.FILE_CREATION_ERROR, e);
        }
    }

    private int[] intGenerator() {
        int[] values = new int[quantity];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < quantity; i++) {
            values[i] = random.nextInt(101);
        }
        return values;
    }
}
