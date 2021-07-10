package org.agency04.software.moneyheist.controllers.member;

import org.agency04.software.moneyheist.dto.MemberDTO;
import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.services.member.MemberService;
import org.agency04.software.moneyheist.validation.requestEntities.MemberCommand;
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
        List<MemberDTO> members = this.memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("{memberId}")
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

    @PostMapping
    public ResponseEntity<?> saveNewMember(@Validated({WholeObjectRequired.class}) @RequestBody final MemberCommand member){
        Integer id = this.memberService.saveMember(member);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/member/" + id.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}/skills")
    public ResponseEntity<?> updateMembersSkills(@Validated({OnlySkillsRequired.class}) @RequestBody MemberCommand member, @PathVariable Integer memberId){
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
