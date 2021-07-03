package org.agency04.software.moneyheist.heist.members;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<?> saveNewMember(@Valid @RequestBody final MemberCommand member){
        Integer id = this.memberService.saveMember(member);

        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/member/" + id.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
