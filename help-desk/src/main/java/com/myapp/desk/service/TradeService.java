package com.myapp.desk.service;

import com.myapp.desk.domain.Trade;
import com.myapp.desk.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Optional<Trade> getTradeById(Long id) {
        return tradeRepository.findById(id);
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Long id) {
        tradeRepository.deleteById(id);
    }

    public List<Trade> getTradesByInstrument(String instrument) {
        return tradeRepository.findByInstrument(instrument);
    }

    public List<Trade> getTradesByDate(Date date) {
        return tradeRepository.findByTradeDate(date);
    }
}

