package org.galatea.starter.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IexHistoricalPrices {
  private BigDecimal close;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal open;
  @JsonAlias(value = "key")
  private String symbol;
  private Integer volume;
  private String date;
}
