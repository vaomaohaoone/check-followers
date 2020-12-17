package org.mephi.timofeev.lab3.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.mephi.timofeev.lab3.properties.BatchProperties;
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
    private final BatchProperties batchProperties;

    /**
     * Метод каждые interval миллисекунд count раз считывает данные из twitter и посылает в ElasticSearch
     * */
    @Override
    public void run() {
        for (int i = 0; i < batchProperties.getCount(); i++) {
            Logger log = LogManager.getLogger("elasticsearch");
            int count = twitter.userOperations().getUserProfile(accountName).getFollowersCount();
            log.info(MarkerManager.getMarker(accountName), count);
            try {
                Thread.sleep(batchProperties.getInterval());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        registeredProfiles.remove(accountName);
    }
}
