package mario.abraham.baristaservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CurrentStatus {

    WAITING("WAITING"),
    IN_PREPARATION("IN_PREPARATION"),
    FINISHED("FINISHED"),
    PICKED_UP("PICKED_UP");

    private final String statusCode;

    CurrentStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    @JsonCreator
    public static CurrentStatus getCurrentStatusFromCode(String value) {
        for (CurrentStatus currentStatus : CurrentStatus.values()) {
            if (currentStatus.getStatusCode().equals(value)) {
                return currentStatus;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.statusCode;
    }
}
