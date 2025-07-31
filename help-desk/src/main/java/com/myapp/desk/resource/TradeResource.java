package com.myapp.desk.resource;

import com.myapp.desk.domain.Trade;
import com.myapp.desk.service.TradeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("trades")
public class TradeResource {

    private final TradeService tradeService;

    public TradeResource(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "AGENT"})
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        trade = tradeService.saveTrade(trade);
        return new ResponseEntity<>(trade, HttpStatus.CREATED);
    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getAllTrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return tradeService.getTradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instrument/{instrument}")
    public ResponseEntity<List<Trade>> getTradesByInstrument(@PathVariable String instrument) {
        return ResponseEntity.ok(tradeService.getTradesByInstrument(instrument));
    }

    @GetMapping("/date")
    public ResponseEntity<List<Trade>> getTradesByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(tradeService.getTradesByDate(date));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        tradeService.deleteTrade(id);
        return ResponseEntity.noContent().build();
    }
}
