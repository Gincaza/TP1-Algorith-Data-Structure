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

    /**
     * Implementação do algoritmo QuickSort
     * Complexidade: O(n log n) no caso médio, O(n²) no pior caso
     */
    public int[] quickSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return numbers;
        }
        
        // Criar cópia para não modificar o array original
        int[] result = numbers.clone();
        quickSortRecursive(result, 0, result.length - 1);
        return result;
    }

    /**
     * Método recursivo do QuickSort
     */
    private void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            // Particionar o array e obter o índice do pivot
            int pivotIndex = partition(array, low, high);

            // Ordenar recursivamente os elementos antes e depois do pivot
            quickSortRecursive(array, low, pivotIndex - 1);
            quickSortRecursive(array, pivotIndex + 1, high);
        }
    }

    /**
     * Particiona o array em torno do pivot
     * Usa o último elemento como pivot (estratégia de Lomuto)
     */
    private int partition(int[] array, int low, int high) {
        // Escolher o último elemento como pivot
        int pivot = array[high];
        
        // Índice do menor elemento (indica a posição correta do pivot)
        int smallerElementIndex = low - 1;

        for (int currentIndex = low; currentIndex < high; currentIndex++) {
            // Se o elemento atual é menor ou igual ao pivot
            if (array[currentIndex] <= pivot) {
                smallerElementIndex++;
                swap(array, smallerElementIndex, currentIndex);
            }
        }

        // Colocar o pivot na posição correta
        swap(array, smallerElementIndex + 1, high);
        return smallerElementIndex + 1;
    }

    /**
     * Troca dois elementos no array
     */
    private void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
