package ch.cern.c2mon.controllers;

import ch.cern.c2mon.models.Tag;
import ch.cern.c2mon.statistics.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Szymon Halastra
 */

@Slf4j
@RestController
@RequestMapping("/get-tag")
public class TagController {

  CacheManager cacheManager;

  @Autowired
  public TagController(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Cacheable("tags")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Tag> pushTag(@RequestBody Tag tag) {
    Tag tagWithId = new Tag(0);

    tagWithId.setParams(tag);

    log.info("Consuming a tag: {}", tagWithId);

    Statistics.getCacheState(cacheManager.getCache("tags"));

    return new ResponseEntity<>(tag, HttpStatus.OK);
  }
}
