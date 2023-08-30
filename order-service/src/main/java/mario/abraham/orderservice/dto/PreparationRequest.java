package mario.abraham.orderservice.dto;

import mario.abraham.orderservice.model.CurrentStatus;

import java.util.Objects;

public class PreparationRequest {

    private String orderNumber;

    private CurrentStatus currentStatus;

    public PreparationRequest() {
    }

    public PreparationRequest(String orderNumber, CurrentStatus currentStatus) {
        this.orderNumber = orderNumber;
        this.currentStatus = currentStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreparationRequest that = (PreparationRequest) o;
        return Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "PreparationRequest{" +
                "orderNumber='" + orderNumber + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
