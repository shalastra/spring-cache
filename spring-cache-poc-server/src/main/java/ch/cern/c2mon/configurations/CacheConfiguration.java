package ch.cern.c2mon.configurations;

import javax.inject.Inject;

import ch.cern.c2mon.C2monCacheConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author Szymon Halastra
 */

@Configuration
@EnableCaching
public class CacheConfiguration {

  private final C2monCacheConfiguration c2monCacheConfiguration;

  @Inject
  public CacheConfiguration(C2monCacheConfiguration c2monCacheConfiguration) {
    this.c2monCacheConfiguration = c2monCacheConfiguration;
  }


  /**TODO:
   * 1. create method definitions in C2monCacheConfiguration interface for name and other stuff people should provide
   * 2. fix detecting an implementation in other module
   * 3.
   */
}
