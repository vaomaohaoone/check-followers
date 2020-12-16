package org.mephi.timofeev.lab3.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.social.twitter.api.Twitter;

import java.util.Set;

/**
 * Task считывания количества подписчиков пользователя twitter и запись данных в ElasticSearch
 */
@RequiredArgsConstructor
public class SendLogsRunner implements Runnable {

    private final Twitter twitter;
    private final String accountName;
    private final Set<String> registeredProfiles;

    /**
     * Метод каждые N секунд M раз считывает данные из twitter и посылает в ElasticSearch
     * */
    @Override
    public void run() {
        //TODO: добавить конфигурирование количества итераций и интервала времени
        for (int i = 0; i < 100; i++) {
            Logger log = LogManager.getLogger("elasticsearch");
            int count = twitter.userOperations().getUserProfile(accountName).getFollowersCount();
            log.info(MarkerManager.getMarker(accountName), count);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        registeredProfiles.remove(accountName);
    }
}
