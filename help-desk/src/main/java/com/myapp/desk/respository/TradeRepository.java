package com.myapp.desk.respository;

import com.myapp.desk.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByInstrument(String instrument);
    List<Trade> findByTradeDate(java.util.Date tradeDate);
}
