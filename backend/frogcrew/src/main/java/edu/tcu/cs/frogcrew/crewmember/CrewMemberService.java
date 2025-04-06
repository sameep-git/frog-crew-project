package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.crewmember.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    private final IdWorker idWorker;


    public CrewMemberService(CrewMemberRepository crewMemberRepository, IdWorker idWorker) {
        this.crewMemberRepository = crewMemberRepository;
        this.idWorker = idWorker;
    }

    public CrewMember findById(String crewId) {
        return crewMemberRepository.findById(crewId)
                .orElseThrow(() -> new CrewMemberNotFoundException(crewId));
    }
}
