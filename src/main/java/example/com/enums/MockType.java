package example.com.enums;

public enum MockType {
    AF_SUCCESS("mocks/af_success.json"),
    AF_FAIL("./af_success_mock.json");
    private String pathToMockFile;
    MockType(String pathToMockFile) {
        this.pathToMockFile = pathToMockFile;
    }
    public String getPathToMockFile() {
        return pathToMockFile;
    }
}
