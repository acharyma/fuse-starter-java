package org.galatea.starter.service;

import java.util.List;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A Feign Declarative REST Client to access endpoints from the Free and Open IEX API to get market
 * data. See https://iextrading.com/developer/docs/
 */
@FeignClient(name = "IEXHISTORICALPRICES", url = "${spring.rest.iexBasePathHistoricalPrices}")
public interface IexClientHistoricalPrices {
  /**
   * Get the last historical prices for the stock symbol passed in. See https://iexcloud.io/docs/api/#historical-prices.
   *
   * @param symbol stock symbol to get historical prices for.
   * @param range the range to get historical prices for
   * @param date the date to get historical prices for
   * @param token the token to pass to the API
   * @return a list of the historical prices for each of the symbols passed in.
   */
  @GetMapping("/stock/{symbol}/chart/{range}/{date}")
  List<IexHistoricalPrices> getHistoricalPricesForSymbol(
      @PathVariable("symbol") String symbol,
      @PathVariable("range") String range,
      @PathVariable("date") String date,
      String token);
}
