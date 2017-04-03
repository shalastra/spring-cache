package ch.cern.c2mon.statistics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

/**
 * @author Szymon Halastra
 */
@Slf4j
public class Statistics {

  public static void getCacheState(Cache cache) {
    log.info("Cache name: {}", cache.getName());
    log.info("Cache type: {}", cache.toString());
    log.info("Current elements in cache: {}", cache.getNativeCache().toString());
  }
}
