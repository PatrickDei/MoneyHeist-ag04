package org.agency04.software.moneyheist.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.dto.request.ConfirmedHeistMembersCommand;
import org.agency04.software.moneyheist.dto.request.HeistCommand;
import org.agency04.software.moneyheist.dto.response.HeistDTO;
import org.agency04.software.moneyheist.dto.response.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.response.HeistRequirementDTO;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.group.Group;
import org.agency04.software.moneyheist.service.HeistService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heist")
public class HeistController {

    private final HeistService heistService;

    public HeistController(HeistService heistService) {
        this.heistService = heistService;
    }

    @GetMapping
    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    public ResponseEntity<List<HeistDTO>> getAllHeists(){
        return ResponseEntity.ok(heistService.findAll());
    }

    @GetMapping("/{heistId}")
    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    public ResponseEntity<HeistDTO> getHeistById(@PathVariable Integer heistId){
        return heistService.findHeist(heistId)
                .map( h -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(h)
                ).orElseGet( () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @GetMapping("/{heistId}/members")
    public ResponseEntity<Set<HeistMembersDTO>> getHeistMembers(@PathVariable Integer heistId){
        HeistDTO heist = heistService.findHeist(heistId).orElse(null);

        if(heist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(heist.getStatus() == HeistStatus.PLANNING)
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        return ResponseEntity.ok(heistService.getHeistMembers(heistId));
    }

    @GetMapping("/{heistId}/skills")
    public ResponseEntity<Set<HeistRequirementDTO>> getHeistRequirements(@PathVariable Integer heistId){
        HeistDTO heist = heistService.findHeist(heistId).orElse(null);

        if(heist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(heistService.getHeistRequirements(heistId));
    }

    @GetMapping("/{heistId}/status")
    @JsonView(Group.JsonViewGroup.HeistStatus.class)
    public ResponseEntity<HeistDTO> getHeistStatus(@PathVariable Integer heistId){
        return heistService.findHeist(heistId)
                .map( h -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(h)
                ).orElseGet( () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @GetMapping("/{heistId}/outcome")
    @JsonView(Group.JsonViewGroup.HeistOutcome.class)
    public ResponseEntity<HeistDTO> getHeistOutcome(@PathVariable Integer heistId){
        HeistDTO heist = heistService.findHeist(heistId).orElse(null);

        if(heist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(heist.getOutcome() == null)
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        return ResponseEntity.ok(heist);
    }

    @GetMapping("/{heistId}/eligible_members")
    @JsonView(Group.JsonViewGroup.EligibleMembers.class)
    public ResponseEntity<HeistDTO> getEligibleHeistMembers(@PathVariable Integer heistId){

        if(!heistService.getHeistStatus(heistId).equals(HeistStatus.PLANNING))
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        HeistDTO returnValue = heistService.getEligibleHeistMembers(heistId);

        if(returnValue == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(returnValue);
    }

    @PostMapping
    public ResponseEntity<?> saveHeist(@Validated({Group.ValidationGroup.WholeObjectRequired.class}) @RequestBody HeistCommand heist) throws ParseException {
        Integer id = this.heistService.saveHeist(heist);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/heist/" + id.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PatchMapping("/{heistId}/skills")
    public ResponseEntity<?> updateHeistSkills(@Validated({Group.ValidationGroup.OnlySkillsRequired.class}) @RequestBody HeistCommand heist,
                                               @PathVariable Integer heistId){
        if(!Arrays.asList(HeistStatus.PLANNING, HeistStatus.READY).contains(heistService.getHeistStatus(heistId)))
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        Integer id = heistService.updateHeistSkills(heist, heistId);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Location", "/heist/" + id.toString() + "/skills");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{heistId}/members")
    public ResponseEntity<?> confirmHeistMembers(@RequestBody @Valid ConfirmedHeistMembersCommand members, @PathVariable Integer heistId){
        if(this.heistService.findHeist(heistId).isEmpty())
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
        if(this.heistService.findHeist(heistId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!this.heistService.heistCanBeStarted(heistId))
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        this.heistService.startHeist(heistId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Location", "/heist/" + heistId.toString() + "/status");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
