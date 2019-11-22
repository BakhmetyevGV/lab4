public class TestResult {
    public String packageId;
    public boolean success;
    public String result;
    public String expected;
    public String testName;
    public Object[] params;

    public TestResult() {
    }

    public TestResult(String packageId, String result,String expected, String testName, Object[] params) {
        this.packageId = packageId;
        this.result = result;
        this.expected = expected;
        this.testName = testName;
        this.params = params;
    }
}
