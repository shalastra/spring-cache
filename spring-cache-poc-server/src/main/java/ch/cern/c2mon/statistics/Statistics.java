package ch.cern.c2mon.statistics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.CachePublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Szymon Halastra
 */
@Slf4j
@Service
@EnableScheduling
public class Statistics {

  public CachePublicMetrics cachePublicMetrics;

  public CacheManager cacheManager;

  @Autowired
  public Statistics(final CachePublicMetrics cachePublicMetrics, final CacheManager cacheManager) {
    this.cachePublicMetrics = cachePublicMetrics;
    this.cacheManager = cacheManager;
  }

  @Scheduled(initialDelay = 10000, fixedDelay = 5000)
  public void displayStatistics() {
//    log.info("Reporting cache");
//    cacheManager.getCacheNames().forEach(cacheName -> this.getCacheState(cacheManager.getCache(cacheName)));

    log.info("Reporting metrics");
    cachePublicMetrics.metrics().forEach(this::logMetrics);
  }

  private void logMetrics(Metric<?> metric) {
    log.info("metric name: {}, metric value: {}, metric timestamp: {}", metric.getName(), metric.getValue(), metric.getTimestamp());
  }

  public void getCacheState(Cache cache) {
    log.info("Cache name: {}", cache.getName());
    log.info("Cache type: {}", cache.toString());
//    log.info("Current elements in cache: {}", cache.getNativeCache().toString()); // displays all elements in a given cache
  }
}
