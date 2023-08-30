package mario.abraham.orderservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CoffeeDrinkType {
    LONG_BLACK("LONG_BLACK"),
    LATTE("LATTE"),
    CAPPUCCINO("CAPPUCCINO"),
    ESPRESSO("ESPRESSO");

    private final String coffeeDrinkCode;

    CoffeeDrinkType(String coffeeDrinkCode) {
        this.coffeeDrinkCode = coffeeDrinkCode;
    }

    public String getCoffeeDrinkCode() {
        return coffeeDrinkCode;
    }

    @JsonCreator
    public static CoffeeDrinkType getCoffeeDrinkTypeFromCode(String value) {
        for (CoffeeDrinkType coffeeDrinkType : CoffeeDrinkType.values()) {
            if (coffeeDrinkType.getCoffeeDrinkCode().equals(value)) {
                return coffeeDrinkType;
            }
        }

        return null;
    }
}
