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
            String filePath = args[0];
            Integer quantity = Integer.parseInt(args[1]);
            
            Manager manager = new Manager(filePath, quantity);
            manager.processFiles();
            
        } catch (NumberFormatException e) {
            System.err.println("Error: Quantity must be a valid number");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Processa arquivos: lê dados, executa ambos algoritmos e compara performance
     */
    public void processFiles() {
        try {
            
            int[] textData = fileReader.readTextFile();

            compareAlgorithms(textData);
            
        } catch (Exception e) {
            System.err.println("Error during processing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Executa ambos algoritmos e compara performance
     */
    private void compareAlgorithms(int[] originalData) {
        System.out.println("\n=== ALGORITHM PERFORMANCE COMPARISON ===");
        
        // Preparar dados (clonar para cada algoritmo)
        int[] dataForShell = originalData.clone();
        int[] dataForQuick = originalData.clone();
        
        // === SHELL SORT ===
        long shellStartTime = System.nanoTime();
        int[] shellSorted = sortAlgorithm.shellSort(dataForShell);
        long shellEndTime = System.nanoTime();
        long shellDuration = shellEndTime - shellStartTime;
        double shellMs = shellDuration / 1_000_000.0;
        
        
        // === QUICK SORT ===
        long quickStartTime = System.nanoTime();
        int[] quickSorted = sortAlgorithm.quickSort(dataForQuick);
        long quickEndTime = System.nanoTime();
        long quickDuration = quickEndTime - quickStartTime;
        double quickMs = quickDuration / 1_000_000.0;
        
        
        // === COMPARAÇÃO ===
        displayPerformanceComparison(shellMs, quickMs);
        
        // === VALIDAÇÃO ===
        validateResults(shellSorted, quickSorted);
        
        // === SALVAR RESULTADOS ===
        saveAlgorithmResults(shellSorted, quickSorted);
    }

    /**
     * Exibe comparação detalhada de performance
     */
    private void displayPerformanceComparison(double shellMs, double quickMs) {
        System.out.println("\n=== PERFORMANCE REPORT ===");
        System.out.println("Shell Sort: " + String.format("%.4f ms", shellMs));
        System.out.println("Quick Sort: " + String.format("%.4f ms", quickMs));
        
        if (shellMs < quickMs) {
            double speedup = quickMs / shellMs;
            System.out.println("Winner: Shell Sort (" + String.format("%.2fx faster", speedup) + ")");
        } else if (quickMs < shellMs) {
            double speedup = shellMs / quickMs;
            System.out.println("Winner: Quick Sort (" + String.format("%.2fx faster", speedup) + ")");
        } else {
            System.out.println("Tie: Both algorithms performed equally");
        }
        
        double difference = Math.abs(shellMs - quickMs);
        System.out.println("Time difference: " + String.format("%.4f ms", difference));
        System.out.println("================================");
    }

    /**
     * Valida se ambos algoritmos produziram o mesmo resultado
     */
    private void validateResults(int[] shellResult, int[] quickResult) {
        boolean resultsMatch = java.util.Arrays.equals(shellResult, quickResult);
        System.out.println("\nResults validation: " + (resultsMatch ? "ASSED" : "FAILED"));
        
        if (!resultsMatch) {
            System.err.println("WARNING: Algorithms produced different results!");
        }
    }

    /**
     * Salva resultados de ambos algoritmos em arquivos separados
     */
    private void saveAlgorithmResults(int[] shellResult, int[] quickResult) {
        try {
    
            saveAlgorithmResult(shellResult, "shellsort");
            
            saveAlgorithmResult(quickResult, "quicksort");
            
        } catch (Exception e) {
            System.err.println("Error saving results: " + e.getMessage());
        }
    }

    /**
     * Salva o resultado de um algoritmo específico
     */
    private void saveAlgorithmResult(int[] sortedData, String algorithmName) {
        try {
            // Preparar dados para texto
            java.util.List<String> lines = new java.util.ArrayList<>();
            for (int value : sortedData) {
                lines.add(String.valueOf(value));
            }
            
            String textOutputPath = algorithmName + "_result.txt";
            fileReader.writeTextToFile(lines, textOutputPath);
            
            String binaryOutputPath = algorithmName + "_result.bin";
            byte[] binaryData = DataConverter.intsToBytes(sortedData);
            fileReader.writeBinaryToFile(binaryData, binaryOutputPath);
            
        } catch (Exception e) {
            System.err.println("Error saving " + algorithmName + " results: " + e.getMessage());
        }
    }
}
