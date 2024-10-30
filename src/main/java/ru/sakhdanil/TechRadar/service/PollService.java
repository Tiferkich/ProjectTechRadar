package ru.sakhdanil.TechRadar.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sakhdanil.TechRadar.exception.TechnologyNotFoundExeption;
import ru.sakhdanil.TechRadar.models.poll.Poll;
import ru.sakhdanil.TechRadar.repository.PollRepository;
import ru.sakhdanil.TechRadar.repository.RingRepository;
import ru.sakhdanil.TechRadar.repository.TechnologyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PollService {
    @Autowired
    private final PollRepository pollRepository;

    @Autowired
    private final TechnologyRepository technologyRepository;

    @Autowired
    private final RingRepository ringRepository;


    @Transactional
    public void addPoll(Poll poll) {
        if (technologyRepository.findByTechId(poll.getTechId()) == null)
            throw new TechnologyNotFoundExeption(poll.getTechId());
        var p = pollRepository.findByTechIdAndUserId(poll.getTechId(), poll.getUserId());
        if (p != null) {
            p.setRingId(poll.getRingId());
            p.setTime(poll.getTime());
            pollRepository.save(p);
        } else {
            pollRepository.save(poll);
        }
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getPollForDashBoard(Long tech_id) {
        List<Long[]> results = pollRepository.countVotesByTechId(tech_id);
        if (results.isEmpty()) throw new TechnologyNotFoundExeption(tech_id);
        Map<String, Long> voteCount = new HashMap<>();
        Map<Long, String> ringNames = new HashMap<>();

        ringRepository.findAll()
                .forEach(ring -> {
                    ringNames.put(ring.getRing_id(), ring.getRing_name());
                    voteCount.put(ring.getRing_name(), 0L);
                });
        for (var result : results) {
            voteCount.put(ringNames.getOrDefault(result[0], "unknown"), result[1]);
        }
        return voteCount;
    }
}

