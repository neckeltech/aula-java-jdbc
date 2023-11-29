package tech.neckel.jdbc.product;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    private Long id;
    private String description;
    private BigDecimal price;
}
