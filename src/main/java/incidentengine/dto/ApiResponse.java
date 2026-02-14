package incidentengine.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private boolean status;
    private int statusCode;
    private String msg;
    private T data;

    public ApiResponse(boolean status, int statusCode, String msg, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }


}

