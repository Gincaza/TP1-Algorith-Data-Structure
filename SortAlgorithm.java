public class SortAlgorithm {
    
    public int[] shellSort(int[] numbers) {
        int length = numbers.length;

        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                int currentValue = numbers[i];
                int position = i;

                while (position >= gap && numbers[position - gap] > currentValue) {
                    numbers[position] = numbers[position - gap];
                    position -= gap;
                }
                numbers[position] = currentValue;
            }
        }

        return numbers;
    }
}
