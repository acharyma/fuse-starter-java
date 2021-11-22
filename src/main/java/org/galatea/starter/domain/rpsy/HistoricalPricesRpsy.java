package org.galatea.starter.domain.rpsy;

import org.galatea.starter.domain.IexHistoricalPrices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalPricesRpsy extends CrudRepository<IexHistoricalPrices, String> {

}
