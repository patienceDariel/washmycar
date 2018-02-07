package za.co.dariel.washmycar.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Models a car wash order
 */
@Getter @Setter
public class Order {

    private Car car;
    private boolean isOutsideWashOnly;
    private boolean isPaid;
    private boolean isWashed;
}
