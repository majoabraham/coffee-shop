package mario.abraham.baristaservice.dto;

import mario.abraham.baristaservice.model.CurrentStatus;

import java.util.Objects;

public class PreparationResponse {

    private Long id;

    private String orderNumber;

    private CurrentStatus currentStatus;

    public PreparationResponse() {
    }

    public PreparationResponse(Long id, String orderNumber, CurrentStatus currentStatus) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.currentStatus = currentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        PreparationResponse that = (PreparationResponse) o;
        return Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "PreparationResponse{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
