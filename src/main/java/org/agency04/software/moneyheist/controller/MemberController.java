package org.agency04.software.moneyheist.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.dto.response_entity.MemberDTO;
import org.agency04.software.moneyheist.groups_and_views.Group;
import org.agency04.software.moneyheist.groups_and_views.View;
import org.agency04.software.moneyheist.service.member.MemberService;
import org.agency04.software.moneyheist.dto.request_entity.MemberCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        return ResponseEntity.ok(this.memberService.findAll());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Integer memberId){
        return memberService.getMemberById(memberId)
                .map( m -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(m)
                ).orElseGet( () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @GetMapping("/{memberId}/skills")
    @JsonView(View.MemberSkills.class)
    public ResponseEntity<MemberDTO> getMemberSkills(@PathVariable Integer memberId){
        return memberService.getMemberById(memberId)
                .map( m -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(m)
                ).orElseGet( () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<?> saveNewMember(@Validated({Group.WholeObjectRequired.class}) @RequestBody final MemberCommand member){
        Integer id = this.memberService.saveMember(member);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/member/" + id.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}/skills")
    public ResponseEntity<?> updateMembersSkills(@Validated({Group.OnlySkillsRequired.class}) @RequestBody MemberCommand member, @PathVariable Integer memberId){
        Integer id = this.memberService.updateMemberSkills(memberId, member);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/member/" + id.toString() + "/skills");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{memberId}/skills/{skillName}")
    public ResponseEntity<?> removeSkillFromMember(@PathVariable Integer memberId, @PathVariable String skillName){

        if(memberService.removeSkillFromMember(memberId, skillName) == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
