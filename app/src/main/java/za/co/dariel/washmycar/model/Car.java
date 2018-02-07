package za.co.dariel.washmycar.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for the cars to be washed
 */
@Getter @Setter
public class Car {

    private String manufacturer;
    private String model;
    private String registrationNumber;
    private String color;
    private CarType type;
    private String ownerFirstName;
    private String ownerLastName;
}

