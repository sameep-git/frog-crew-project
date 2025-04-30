package edu.tcu.cs.frogcrew.availability;

import edu.tcu.cs.frogcrew.availability.converter.AvailabilityDTOToAvailabilityConverter;
import edu.tcu.cs.frogcrew.availability.converter.AvailabilityToAvailabilityDTOConverter;
import edu.tcu.cs.frogcrew.availability.dto.AvailabilityDTO;
import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberService;
import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.GameService;
import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frogcrew/api/v1/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;
    private final CrewMemberService crewMemberService;
    private final GameService gameService;
    private final AvailabilityToAvailabilityDTOConverter availabilityToAvailabilityDTOConverter;
    private final AvailabilityDTOToAvailabilityConverter availabilityDTOToAvailabilityConverter;

    public AvailabilityController(AvailabilityService availabilityService, CrewMemberService crewMemberService, GameService gameService, AvailabilityToAvailabilityDTOConverter availabilityToAvailabilityDTOConverter, AvailabilityDTOToAvailabilityConverter availabilityDTOToAvailabilityConverter) {
        this.availabilityService = availabilityService;
        this.crewMemberService = crewMemberService;
        this.gameService = gameService;
        this.availabilityToAvailabilityDTOConverter = availabilityToAvailabilityDTOConverter;
        this.availabilityDTOToAvailabilityConverter = availabilityDTOToAvailabilityConverter;
    }

    // Use case 7: Crew Member Submits Availability
    @PostMapping
    public Result addAvailability(@Valid @RequestBody AvailabilityDTO availabilityDTO) {
        CrewMember availableCrewMember = this.crewMemberService.findById(availabilityDTO.crewMemberId());
        Game availableGame = this.gameService.findById(availabilityDTO.gameId());
        Availability availability = availabilityDTOToAvailabilityConverter.convert(availabilityDTO);

        Availability savedAvailability = this.availabilityService.addAvailability(availability);
        savedAvailability.setGame(availableGame);
        savedAvailability.setCrewMember(availableCrewMember);

        AvailabilityDTO savedAvailabilityDTO = availabilityToAvailabilityDTOConverter.convert(savedAvailability);
        return new Result(true, StatusCode.SUCCESS, "New availability added", savedAvailabilityDTO);
    }
}
