package ch.cern.c2mon.components;

import ch.cern.c2mon.models.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Szymon Halastra
 */
@Slf4j
@Component
@EnableScheduling
public class RestComponent {

  @Scheduled(fixedDelay = 2000)
  @EventListener(ContextRefreshedEvent.class)
  public void init() {
    RestTemplate restTemplate = new RestTemplate();

    Tag tag = new Tag();
    try {
      RestTemplate template = new RestTemplate();
      Tag returns = template.postForObject("http://localhost:8080/get-tag", tag, Tag.class, "1");

      log.info("Posted tag: {}", returns.toString());
    } catch (HttpClientErrorException ex) {
      log.error(ex.toString());
    }
  }
}
