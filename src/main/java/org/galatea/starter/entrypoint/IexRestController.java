package org.galatea.starter.entrypoint;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import net.sf.aspect4log.Log.Level;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.galatea.starter.service.IexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Log(enterLevel = Level.INFO, exitLevel = Level.INFO)
@Validated
@RestController
@RequiredArgsConstructor
public class IexRestController {

  @NonNull
  private IexService iexService;



  /**
   * Exposes an endpoint to get all of the symbols available on IEX.
   *
   * @return a list of all IexStockSymbols.
   */
  @GetMapping(value = "${mvc.iex.getAllSymbolsPath}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<IexSymbol> getAllStockSymbols() {
    return iexService.getAllSymbols();
  }

  /**
   * Get the last traded price for each of the symbols passed in.
   *
   * @param symbols list of symbols to get last traded price for.
   * @return a List of IexLastTradedPrice objects for the given symbols.
   */
  @GetMapping(value = "${mvc.iex.getLastTradedPricePath}", produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public List<IexLastTradedPrice> getLastTradedPrice(
      @RequestParam(value = "symbols") final List<String> symbols) {
    return iexService.getLastTradedPriceForSymbols(symbols);
  }

  /**
   * Get the historical prices for the symbol passed in.
   *
   * @param symbol to get last historical prices for.
   * @param range to get the range for the historical prices -- default to 1 m (same as the IEX API)
   * @param date to get the date for the historical prices -- default to empty (same as the IEX API)
   * @return a List of Historical Prices objects for the given symbol.
   */
  @GetMapping(value = "${mvc.iex.getHistoricalPricesPath}", produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public List<IexHistoricalPrices> getHistoricalPrices(
      @RequestParam(value = "symbol") final String symbol,
      @RequestParam(value = "range", defaultValue = "1m") final String range,
      @RequestParam(value = "date", defaultValue = "") final String date) {
    if (symbol.compareTo("") == 0) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "entity not found"
      );
    }
    if (range.length() >= 3) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "range not valid"
      );
    }
    if (date.length() != 8 && date.length() > 0) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "date not valid"
      );
    }

    return iexService.getHistoricalPricesForSymbol(symbol, range, date);
  }

}
