package mario.abraham.orderservice.dto;

import mario.abraham.orderservice.model.CoffeeDrinkType;
import mario.abraham.orderservice.model.CoffeeSize;
import mario.abraham.orderservice.model.CurrentStatus;
import mario.abraham.orderservice.model.MilkType;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderResponse {

    private Long id;

    private CoffeeDrinkType coffeeDrinkType;

    private CoffeeSize coffeeSize;

    private MilkType milkType;

    private boolean onSite;

    private BigDecimal price;

    private String orderNumber;

    private CurrentStatus currentStatus;

    public OrderResponse() {
    }

    public OrderResponse(Long id, CoffeeDrinkType coffeeDrinkType, CoffeeSize coffeeSize, MilkType milkType, boolean onSite, BigDecimal price, String orderNumber, CurrentStatus currentStatus) {
        this.id = id;
        this.coffeeDrinkType = coffeeDrinkType;
        this.coffeeSize = coffeeSize;
        this.milkType = milkType;
        this.onSite = onSite;
        this.price = price;
        this.orderNumber = orderNumber;
        this.currentStatus = currentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoffeeDrinkType getCoffeeDrinkType() {
        return coffeeDrinkType;
    }

    public void setCoffeeDrinkType(CoffeeDrinkType coffeeDrinkType) {
        this.coffeeDrinkType = coffeeDrinkType;
    }

    public CoffeeSize getCoffeeSize() {
        return coffeeSize;
    }

    public void setCoffeeSize(CoffeeSize coffeeSize) {
        this.coffeeSize = coffeeSize;
    }

    public MilkType getMilkType() {
        return milkType;
    }

    public void setMilkType(MilkType milkType) {
        this.milkType = milkType;
    }

    public boolean isOnSite() {
        return onSite;
    }

    public void setOnSite(boolean onSite) {
        this.onSite = onSite;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        OrderResponse that = (OrderResponse) o;
        return Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", coffeeDrinkType=" + coffeeDrinkType +
                ", coffeeSize=" + coffeeSize +
                ", milkType=" + milkType +
                ", onSite=" + onSite +
                ", price=" + price +
                ", orderNumber='" + orderNumber + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
