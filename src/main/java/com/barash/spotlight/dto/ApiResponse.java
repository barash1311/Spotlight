package com.barash.spotlight.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String  message;
    private T       data;
    private Instant timestamp = Instant.now();

    public ApiResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public Instant getTimestamp() { return timestamp; }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.success = true;
        resp.data = data;
        return resp;
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        ApiResponse<T> resp = ok(data);
        resp.message = message;
        return resp;
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.success = false;
        resp.message = message;
        return resp;
    }

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public static class ApiResponseBuilder<T> {
        private boolean success;
        private String  message;
        private T       data;
        private Instant timestamp = Instant.now();

        public ApiResponseBuilder<T> success(boolean s) { this.success = s; return this; }
        public ApiResponseBuilder<T> message(String m) { this.message = m; return this; }
        public ApiResponseBuilder<T> data(T d) { this.data = d; return this; }
        public ApiResponseBuilder<T> timestamp(Instant t) { this.timestamp = t; return this; }
        
        public ApiResponse<T> build() {
            ApiResponse<T> r = new ApiResponse<>();
            r.success = this.success;
            r.message = this.message;
            r.data = this.data;
            r.timestamp = this.timestamp;
            return r;
        }
    }
}
