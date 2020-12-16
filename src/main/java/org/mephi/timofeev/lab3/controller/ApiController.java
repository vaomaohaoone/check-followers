package org.mephi.timofeev.lab3.controller;

import lombok.RequiredArgsConstructor;
import org.mephi.timofeev.lab3.service.SendLogsRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final Twitter twitter;
    private final TaskExecutor taskExecutor;
    private final Set<String> registeredProfiles = new ConcurrentSkipListSet<>();

    @PostMapping("/run/{account}")
    public HttpStatus addProfile(@PathVariable String account) {
        try {
            twitter.userOperations().getUserProfile(account);
            if (!registeredProfiles.contains(account)) {
                taskExecutor.execute(new SendLogsRunner(twitter, account, registeredProfiles));
                registeredProfiles.add(account);
                return HttpStatus.CREATED;
            } else
                return HttpStatus.ALREADY_REPORTED;
        }
        catch (ResourceNotFoundException ex) {
            return HttpStatus.NO_CONTENT;
        }
    }
}
