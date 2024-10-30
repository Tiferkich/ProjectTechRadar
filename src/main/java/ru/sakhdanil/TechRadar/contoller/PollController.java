package ru.sakhdanil.TechRadar.contoller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ru.sakhdanil.TechRadar.config.FuncName;
import ru.sakhdanil.TechRadar.models.poll.Poll;
import ru.sakhdanil.TechRadar.models.poll.PollRequest;
import ru.sakhdanil.TechRadar.models.user.User;
import ru.sakhdanil.TechRadar.service.PollService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@Tag(name = "Опросы", description = "Реализует endpoits для работы с опросами")
@RestController
@RequiredArgsConstructor
public class PollController {

    @Autowired
    private final PollService pollService;


    @FuncName(name = "Голосование за распределение продуктов")
    @Operation(
            summary = "Добавление результата опроса",
            description = "Позволяет пользователю проголосовать за технологию"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Poll is created"),
            @ApiResponse(responseCode = "404", description = "Not find tech", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = @Content)})
    @PostMapping("/poll")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> makePollByUser(@RequestBody PollRequest pollRequest, @AuthenticationPrincipal User user) {
        var poll = Poll.builder().userId(user.getUserId())
                .time(Timestamp.from(Instant.now()).toString())
                .techId(pollRequest.getTechId())
                .ringId(pollRequest.getRingId()).build();

        pollService.addPoll(poll);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Результат опроса успешно добавлен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @FuncName(name = "Просмотр дашборда по продукту")
    @Operation(summary = "Получить данные для дешборда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not find tech", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = @Content)})
    @GetMapping("/poll")
    //@PreAuthorize("hasAnyRole('TECH_LEAD')")
    public Map<String, Long> getPollByTechId(@RequestParam Long techId) {
        return pollService.getPollForDashBoard(techId);
    }
}
