package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frogcrew/api/v1/crew")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping("/{crewId}")
    public Result findCrewMemberById(@PathVariable String crewId) {
        CrewMember foundCrewMember = crewMemberService.findById(crewId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundCrewMember);
    }
}
