package ch.cern.c2mon.runners;

import ch.cern.c2mon.models.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Szymon Halastra
 */
@Slf4j
@Component
public class DemoRunner {

  @EventListener(ContextRefreshedEvent.class)
  public void init() {
    log.info("Posting tag update");
    RestTemplate restTemplate = new RestTemplate();

    Tag tag = new Tag();
    
    RestTemplate template = new RestTemplate();
    Tag returns = template.postForObject("http://localhost:8080/get-tag", tag, Tag.class, "1");
  }
}
