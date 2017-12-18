package worldvisionsoft.com.livedataviewholderdemo.repo.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/18/2017.
 */

public class ApiResponse <T> {

    @SerializedName("Data")
    private T data;

    @SerializedName("StatusCode")
    private int statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("MessageDetail")
    private String messageDetail;

    @SerializedName("Errors")
    private T errors;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }
}
