package org.agency04.software.moneyheist.heists;

import org.agency04.software.moneyheist.heists.validation.HeistCommand;
import org.agency04.software.moneyheist.interfaces.WholeObjectRequired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heist")
public class HeistController {

    private final HeistService heistService;

    public HeistController(HeistService heistService) {
        this.heistService = heistService;
    }

    @GetMapping
    public ResponseEntity<List<HeistDTO>> getAllHeists(){
        return ResponseEntity.ok(heistService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> saveHeist(@Validated({WholeObjectRequired.class}) HeistCommand heist){
        Integer id;

        
    }
}
