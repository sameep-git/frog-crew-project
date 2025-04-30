package edu.tcu.cs.frogcrew.assignment;


import edu.tcu.cs.frogcrew.assignment.dto.CrewAssignmentDto;
import edu.tcu.cs.frogcrew.assignment.dto.CrewListDto;
import edu.tcu.cs.frogcrew.system.Result;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("frogcrew/api/v1")
public class AssignmentController {

//    private static Logger log = new Logger(AssignmentController.class);
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // Use Case 6: Crew Member Views Crew List
    @GetMapping("/crewList/{gameId}")
    public Result getCrewListForGame(@PathVariable Integer gameId) {
        CrewListDto crewList = assignmentService.getCrewListForGame(gameId);


        return new Result(true, HttpStatus.OK.value(), "Find Success", crewList);
    }

    // Use Case 23: Admin adds new crew Schedule
    @PostMapping("/crewSchedule/{gameId}")
    public Result addCrewSchedule(@PathVariable Integer gameId, @RequestBody @Valid List<CrewAssignmentDto> assignments) {
        List<CrewAssignmentDto> savedAssignments = assignmentService.saveCrewSchedule(gameId, assignments);
        return new Result(true, HttpStatus.OK.value(), "Add Success", savedAssignments);
    }
}
