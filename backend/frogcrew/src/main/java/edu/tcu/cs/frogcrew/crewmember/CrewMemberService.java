package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.crewmember.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    private final IdWorker idWorker;


    public CrewMemberService(CrewMemberRepository crewMemberRepository, IdWorker idWorker) {
        this.crewMemberRepository = crewMemberRepository;
        this.idWorker = idWorker;
    }

    public CrewMember findById(Integer crewId) {
        return crewMemberRepository.findById(String.valueOf(crewId))
                .orElseThrow(() -> new CrewMemberNotFoundException(crewId));
    }

    public List<CrewMember> findAll() {
        return crewMemberRepository.findAll();
    }

    public CrewMember save(CrewMember crewMember) {
        crewMember.setId(Integer.valueOf(idWorker.nextId() + ""));
        return this.crewMemberRepository.save(crewMember);
    }

    public void delete(Integer crewId) {
        crewMemberRepository.findById(String.valueOf(crewId)).orElseThrow(
                () -> new CrewMemberNotFoundException(crewId));
        crewMemberRepository.deleteById(String.valueOf(crewId));
    }
}
