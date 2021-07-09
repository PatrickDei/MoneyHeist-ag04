package org.agency04.software.moneyheist.controllers.heist;

import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.services.heist.HeistService;
import org.agency04.software.moneyheist.validation.requestEntities.HeistCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<?> saveHeist(@Validated({WholeObjectRequired.class}) @RequestBody HeistCommand heist) throws ParseException {
        Integer id = this.heistService.saveHeist(heist);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/heist/" + id.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PatchMapping("/{heistId}/skills")
    public ResponseEntity<?> updateHeistSkills(@Validated({OnlySkillsRequired.class}) @RequestBody HeistCommand heist,
                                               @PathVariable Integer heistId){
        Integer id = heistService.updateHeistSkills(heist, heistId);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Location", "/heist/" + id.toString() + "/skills");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
