package com.SDP.project.responses;

public class TaskStatusUpdateResponse {
    private boolean success;
    private String message;

    public TaskStatusUpdateResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
