package org.agency04.software.moneyheist.controllers.heist;

import org.agency04.software.moneyheist.dto.EligibleHeistMembersDTO;
import org.agency04.software.moneyheist.dto.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.services.heist.HeistService;
import org.agency04.software.moneyheist.validation.requestEntities.ConfirmedHeistMembersCommand;
import org.agency04.software.moneyheist.validation.requestEntities.HeistCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{heistId}/eligible_members")
    public ResponseEntity<EligibleHeistMembersDTO> getEligibleHeistMembers(@PathVariable Integer heistId){

        EligibleHeistMembersDTO returnValue = heistService.getEligibleHeistMembers(heistId);

        if(returnValue == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(returnValue);
    }

    @PutMapping("/{heistId}/members")
    public ResponseEntity<?> confirmHeistMembers(@RequestBody @Valid ConfirmedHeistMembersCommand members, @PathVariable Integer heistId){
        if(this.heistService.findHeistById(heistId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(this.heistService.getHeistStatus(heistId) != HeistStatus.PLANNING)
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        Integer id = this.heistService.confirmHeistMembers(heistId, members.getMembers());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Location", "/heist/" + id.toString() + "/members");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{heistId}/start")
    public ResponseEntity<?> startHeist(@PathVariable Integer heistId){
        if(this.heistService.findHeistById(heistId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!this.heistService.heistCanBeStarted(heistId))
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        this.heistService.startHeist(heistId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Location", "/heist/" + heistId.toString() + "/status");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
