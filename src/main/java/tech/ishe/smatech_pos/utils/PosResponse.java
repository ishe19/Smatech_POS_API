package tech.ishe.smatech_pos.utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PosResponse {
    private String message;
    private boolean status;
    private List<String> errors;
    private Object data;

    public PosResponse(String message) {
        this.message = message;
    }

    public PosResponse(Object data) {
        this.data = data;
    }

    public PosResponse(String message, List<String> errors, boolean status) {
        this.message = message;
        this.errors = errors;
        this.status = status;
    }

    public PosResponse(String message, Object data, boolean status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
}
