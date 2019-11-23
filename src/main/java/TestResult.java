public class TestResult {
    public String packageId;
    public String testName;
    public boolean success;
    public String result;
    public String expected;

    public Object[] params;

    public TestResult() {
    }

    public TestResult(String packageId, String testName, boolean success, String result,String expected, Object[] params) {
        this.packageId = packageId;
        this.testName = testName;
        this.success = success;
        this.result = result;
        this.expected = expected;
        this.params = params;
    }
}
