package za.co.dariel.washmycar.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Models price information for the car
 */
@Getter @Setter
public class Price {

    private CarType carType;
    private BigDecimal price;
}
