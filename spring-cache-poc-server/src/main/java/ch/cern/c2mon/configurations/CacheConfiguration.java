package ch.cern.c2mon.configurations;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * @author Szymon Halastra
 */

@org.springframework.context.annotation.Configuration
@EnableCaching
public class CacheConfiguration {

  @Bean
  public CacheManager cacheManager() {
    CacheManager cacheManager = new ConcurrentMapCacheManager("tags");

    return cacheManager;
  }
}
