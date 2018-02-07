package za.co.dariel.washmycar.model;

import lombok.Getter;

/**
 * Lists the types of cars which will be washed
 */
@Getter
public enum CarType {

    HATCHBACK("hatchback"), SUV("suv");

    private final String carType;

    CarType(String carType) {
        this.carType = carType;
    }

    private String getCarType() {
        return carType;
    }
}
