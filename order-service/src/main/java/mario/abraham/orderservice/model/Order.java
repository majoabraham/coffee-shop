package mario.abraham.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private CoffeeDrinkType coffeeDrinkType;

    @Column(nullable = false)
    private CoffeeSize coffeeSize;

    private MilkType milkType;

    @Column(nullable = false)
    private boolean onSite;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    public Order() {
    }

    public Order(Long id, CoffeeDrinkType coffeeDrinkType, CoffeeSize coffeeSize, MilkType milkType, boolean onSite, BigDecimal price, String orderNumber) {
        this.id = id;
        this.coffeeDrinkType = coffeeDrinkType;
        this.coffeeSize = coffeeSize;
        this.milkType = milkType;
        this.onSite = onSite;
        this.price = price;
        this.orderNumber = orderNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", coffeeDrinkType=" + coffeeDrinkType +
                ", coffeeSize=" + coffeeSize +
                ", milkType=" + milkType +
                ", onSite=" + onSite +
                ", price=" + price +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
