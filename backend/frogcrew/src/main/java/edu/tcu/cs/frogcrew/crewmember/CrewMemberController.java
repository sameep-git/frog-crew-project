package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.crewmember.converter.CrewMemberToCrewMemberDtoConverter;
import edu.tcu.cs.frogcrew.crewmember.converter.CrewMemberToCrewMemberSimpleDtoConverter;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberDto;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberSimpleDto;
import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frogcrew/api/v1/crew")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    private final CrewMemberToCrewMemberDtoConverter crewMemberToCrewMemberDtoConverter;

    private final CrewMemberToCrewMemberSimpleDtoConverter crewMemberToCrewMemberSimpleDtoConverter;

    public CrewMemberController(CrewMemberService crewMemberService, CrewMemberToCrewMemberDtoConverter crewMemberToCrewMemberDtoConverter, CrewMemberToCrewMemberSimpleDtoConverter crewMemberToCrewMemberSimpleDtoConverter) {
        this.crewMemberService = crewMemberService;
        this.crewMemberToCrewMemberDtoConverter = crewMemberToCrewMemberDtoConverter;
        this.crewMemberToCrewMemberSimpleDtoConverter = crewMemberToCrewMemberSimpleDtoConverter;
    }

    @GetMapping("/{crewId}")
    public Result findCrewMemberById(@PathVariable Long crewId) {
        CrewMember foundCrewMember = crewMemberService.findById(crewId);
        CrewMemberDto foundCrewMemberDto = crewMemberToCrewMemberDtoConverter.convert(foundCrewMember);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundCrewMemberDto);
    }

    @GetMapping
    public Result findAllCrewMembers() {
        List<CrewMember> foundCrewMembers = crewMemberService.findAll();

        if (foundCrewMembers.isEmpty()) {
            return new Result(true, StatusCode.NO_CONTENT, "No Crew Members registered in the system.");
        }

        List<CrewMemberSimpleDto> dtos = foundCrewMembers.stream()
                .map(crewMemberToCrewMemberSimpleDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", dtos);
    }

    @PostMapping
    public Result addCrewMember(@RequestBody CrewMember crewMember) {
        CrewMember savedCrewMember = crewMemberService.save(crewMember);
        CrewMemberDto savedCrewMemberDto = crewMemberToCrewMemberDtoConverter.convert(savedCrewMember);
        return new Result(true, StatusCode.SUCCESS, "Add One Success", savedCrewMemberDto);
    }

    @DeleteMapping("/{crewId}")
    public Result deleteCrewMember(@PathVariable Integer crewId) {
        crewMemberService.delete(crewId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
