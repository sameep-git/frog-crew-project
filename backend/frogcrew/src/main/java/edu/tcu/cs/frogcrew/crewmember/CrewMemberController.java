package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Result findAllCrewMembers() {
        List<CrewMember> foundCrewMembers = crewMemberService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundCrewMembers);
    }

    @PostMapping
    public Result addCrewMember(@RequestBody CrewMember crewMember) {
        crewMemberService.save(crewMember);
        return new Result(true, StatusCode.SUCCESS, "Add One Success", crewMember);
    }

    @DeleteMapping("/{crewId}")
    public Result deleteCrewMember(@PathVariable String crewId) {
        crewMemberService.delete(crewId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
