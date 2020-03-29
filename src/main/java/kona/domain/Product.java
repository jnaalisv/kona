package kona.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Product(
        long id
        , long version
        , LocalDateTime created
        , String name
        , String code
        , ProductType productType
        , BigDecimal price
) { }
