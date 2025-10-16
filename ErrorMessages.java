public class ErrorMessages {
    // File validation messages
    public static final String FILE_PATH_NULL = "File path cannot be null";
    public static final String FILE_NOT_FOUND = "File not found";
    public static final String FILE_NOT_EXISTS = "File doesn't exist";
    
    // Data validation messages
    public static final String DATA_NULL = "Data cannot be null";
    public static final String LINES_NULL = "Lines cannot be null";
    public static final String VALUES_EMPTY = "Values cannot be empty";
    
    // Quantity validation messages
    public static final String QUANTITY_NULL = "Quantity cannot be null";
    public static final String QUANTITY_INVALID = "Quantity must be greater than zero";
    public static final String QUANTITY_PATH_INVALID = "File path and quantity must be valid";
    
    // Conversion messages
    public static final String CONVERSION_ERROR = "Error converting to number";
    
    // File creation messages
    public static final String FILE_CREATION_ERROR = "Error while creating files";
    public static final String TEXT_FILE_WRITE_ERROR = "Error writing text file";
    
    // Success messages - com placeholder para String.format()
    public static final String FILES_CREATED = "Files created successfully: %s and %s";
    
    // Prevent instantiation
    private ErrorMessages() {}
}