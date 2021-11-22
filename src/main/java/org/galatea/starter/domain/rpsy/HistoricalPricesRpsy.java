package org.galatea.starter.domain.rpsy;

import java.util.List;
import java.util.Optional;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.springframework.data.repository.CrudRepository;

public interface HistoricalPricesRpsy extends CrudRepository<IexHistoricalPrices, String> {
  /**
   * Retrieves all entities with the given symbol.
   */
  List<IexHistoricalPrices> findBySymbol(String symbol);

  /**
   * Saves historical prices I think.
   * @param entity the historical price to save
   */
  <S extends IexHistoricalPrices> S save(S entity);
}
