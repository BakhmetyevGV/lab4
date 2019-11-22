public class ResponseMessage {
    public String packageId;
    public boolean success;
    public TestResult[] results;

    public ResponseMessage(){

    }

    public ResponseMessage(String packageId, TestResult[] results){
        this.packageId = packageId;
        this.results = results;
    }
}
