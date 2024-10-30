package ru.sakhdanil.TechRadar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sakhdanil.TechRadar.exception.TechnologyNotFoundExeption;
import ru.sakhdanil.TechRadar.models.Technology;
import ru.sakhdanil.TechRadar.models.poll.Poll;
import ru.sakhdanil.TechRadar.repository.PollRepository;
import ru.sakhdanil.TechRadar.repository.RingRepository;
import ru.sakhdanil.TechRadar.repository.TechnologyRepository;
import ru.sakhdanil.TechRadar.service.PollService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PollServiceTest {

    @Mock
    private PollRepository pollRepository;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private RingRepository ringRepository;

    @InjectMocks
    private PollService pollService;

    private Poll testPoll;

    @BeforeEach
    void setUp() {
        testPoll = new Poll(/* ваши параметры для конструктора */);
    }

    @Test
    void addPoll_whenTechnologyExists_shouldSavePoll() {
        when(technologyRepository.findByTechId(testPoll.getTechId())).thenReturn(new Technology());
        when(pollRepository.findByTechIdAndUserId(testPoll.getTechId(), testPoll.getUserId())).thenReturn(null);

        pollService.addPoll(testPoll);

        verify(pollRepository).save(testPoll);
    }

    @Test
    void addPoll_whenTechnologyDoesNotExist_shouldThrowTechnologyNotFoundException() {
        when(technologyRepository.findByTechId(testPoll.getTechId())).thenReturn(null);

        assertThrows(TechnologyNotFoundExeption.class, () -> pollService.addPoll(testPoll));
    }

    @Test
    void addPoll_whenPollExists_shouldUpdatePoll() {
        Poll existingPoll = new Poll(/* параметры для существующего опроса */);
        when(technologyRepository.findByTechId(testPoll.getTechId())).thenReturn(new Technology());
        when(pollRepository.findByTechIdAndUserId(testPoll.getTechId(), testPoll.getUserId())).thenReturn(existingPoll);

        pollService.addPoll(testPoll);

        assertEquals(testPoll.getRingId(), existingPoll.getRingId());
        assertEquals(testPoll.getTime(), existingPoll.getTime());
        verify(pollRepository).save(existingPoll);
    }



    @Test
    void getPollForDashBoard_whenNoResults_shouldThrowTechnologyNotFoundException() {
        Long techId = 1L;
        when(pollRepository.countVotesByTechId(techId)).thenReturn(Collections.emptyList());

        assertThrows(TechnologyNotFoundExeption.class, () -> pollService.getPollForDashBoard(techId));
    }
}
