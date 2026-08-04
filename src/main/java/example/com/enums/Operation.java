package example.com.enums;

public enum Operation {
    SELECT("select"),
    DELETE("delete"),
    INSERT("insert"),
    UPDATE("update");
    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }
    public String getOperation() {
        return operation;
    }
}
