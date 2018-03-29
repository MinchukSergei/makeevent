package by.bsuir.ksis.bigdata.makeevent.util;

import java.util.HashMap;
import java.util.Map;

public class RequestResponse<T> {
    private boolean success;
    private Map<String, T> requestData;
    private Map<String, Object> errors;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, T> getRequestData() {
        return requestData;
    }

    public void setRequestData(Map<String, T> requestData) {
        this.requestData = requestData;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public static <T> RequestResponse<T> createSuccessResponse() {
        RequestResponse<T> response = createResponse();
        response.setSuccess(true);
        return response;
    }

    public static <T> RequestResponse<T> createUnSuccessResponse() {
        RequestResponse<T> response = createResponse();
        response.setSuccess(false);
        return response;
    }

    private static <T> RequestResponse<T> createResponse() {
        RequestResponse<T> requestResponse = new RequestResponse<>();
        requestResponse.setRequestData(new HashMap<>());
        requestResponse.setErrors(new HashMap<>());
        return requestResponse;
    }
}
