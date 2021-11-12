package org.galatea.starter.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * A layer for transformation, aggregation, and business required when retrieving data from IEX.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IexService {

  @NonNull
  private IexClient iexClient;

  @NonNull
  private IexClientHistoricalPrices iexClientHistoricalPrices;

  @Value("${apiKey}")
  private String apiKey;





  /**
   * Get all stock symbols from IEX.
   *
   * @return a list of all Stock Symbols from IEX.
   */
  public List<IexSymbol> getAllSymbols() {
    return iexClient.getAllSymbols();
  }

  /**
   * Get the last traded price for each Symbol that is passed in.
   *
   * @param symbols the list of symbols to get a last traded price for.
   * @return a list of last traded price objects for each Symbol that is passed in.
   */
  public List<IexLastTradedPrice> getLastTradedPriceForSymbols(final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbols(symbols.toArray(new String[0]));
    }
  }

  /**
   * Get the last traded price for each Symbol that is passed in.
   *
   * @param symbol the symbol to get a historical prices for.
   * @param range the range to get historical prices for
   * @param date the date to get historical prices for
   * @return a list of historical prices for the Symbol that is passed in.
   */
  public List<IexHistoricalPrices> getHistoricalPricesForSymbol(
      final String symbol,
      final String range,
      final String date) {
    if (symbol.compareTo("") == 0) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "entity not found"
      );
    }

    return iexClientHistoricalPrices
        .getHistoricalPricesForSymbol(symbol, range, date, apiKey);
  }
}
