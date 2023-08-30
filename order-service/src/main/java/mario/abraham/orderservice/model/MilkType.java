package mario.abraham.orderservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MilkType {
    COW("COW"),
    SOY("SOY"),
    ALMOND("ALMOND");

    private final String milkCode;

    MilkType(String milkCode) {
        this.milkCode = milkCode;
    }

    public String getMilkCode() {
        return milkCode;
    }

    @JsonCreator
    public static MilkType getMilkTypeFromCode(String value) {
        for (MilkType milkType : MilkType.values()) {
            if (milkType.getMilkCode().equals(value)) {
                return milkType;
            }
        }

        return null;
    }
}
