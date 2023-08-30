package mario.abraham.orderservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CoffeeSize {
    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private final String coffeeSizeCode;

    CoffeeSize(String coffeeSizeCode) {
        this.coffeeSizeCode = coffeeSizeCode;
    }

    public String getCoffeeSizeCode() {
        return coffeeSizeCode;
    }

    @JsonCreator
    public static CoffeeSize getCoffeeSizeFromCode(String value) {
        for (CoffeeSize coffeeSize : CoffeeSize.values()) {
            if (coffeeSize.getCoffeeSizeCode().equals(value)) {
                return coffeeSize;
            }
        }

        return null;
    }
}
