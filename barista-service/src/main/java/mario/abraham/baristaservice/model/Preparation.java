package mario.abraham.baristaservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Preparation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private CurrentStatus currentStatus;

    public Preparation() {
    }

    public Preparation(Long id, String orderNumber, CurrentStatus currentStatus) {
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
        Preparation that = (Preparation) o;
        return Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "Preparation{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
