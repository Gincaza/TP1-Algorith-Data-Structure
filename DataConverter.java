import java.nio.ByteBuffer;

public class DataConverter {

    public static int[] bytesToInts(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("Array de bytes não pode ser nulo");
        }
        
        if (bytes.length % 4 != 0) {
            throw new IllegalArgumentException("Array de bytes deve ter tamanho múltiplo de 4");
        }
        
        int numInts = bytes.length / 4;
        int[] numbers = new int[numInts];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        
        for (int i = 0; i < numInts; i++) {
            numbers[i] = buffer.getInt();
        }
        
        return numbers;
    }
    
    public static byte[] intsToBytes(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array de inteiros não pode ser nulo");
        }
        
        ByteBuffer buffer = ByteBuffer.allocate(numbers.length * 4);
        
        for (int number : numbers) {
            buffer.putInt(number);
        }
        
        return buffer.array();
    }
}
