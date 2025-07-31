package com.myapp.desk.respository;

import com.myapp.desk.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Instrument findBySymbol(String symbol);
    Instrument findByIsin(String isin);
}

