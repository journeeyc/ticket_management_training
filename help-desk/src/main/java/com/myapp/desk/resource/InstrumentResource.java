package com.myapp.desk.resource;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.service.InstrumentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instruments")
public class InstrumentResource {

    private final InstrumentService instrumentService;

    public InstrumentResource(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) {
        instrument = instrumentService.saveInstrument(instrument);
        return new ResponseEntity<>(instrument, HttpStatus.CREATED);
    }

    @GetMapping
    @RolesAllowed({"ADMIN", "AGENT"})
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        return ResponseEntity.ok(instrumentService.getAllInstruments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable Long id) {
        return instrumentService.getInstrumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
        instrumentService.deleteInstrument(id);
        return ResponseEntity.noContent().build();
    }
}
