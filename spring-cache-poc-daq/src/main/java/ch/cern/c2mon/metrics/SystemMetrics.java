package ch.cern.c2mon.metrics;

import java.lang.management.ManagementFactory;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.core.Ordered;

/**
 * @author Szymon Halastra
 */
public class SystemMetrics implements PublicMetrics, Ordered {

  private long timestamp;

  public SystemMetrics() {
    this.timestamp = System.currentTimeMillis();
  }


  @Override
  public Collection<Metric<?>> metrics() {
    Collection<Metric<?>> result = new LinkedHashSet<>();

    addBasicMetrics(result);

    return result;
  }

  public Map<String, Long> getMetricsMap() {
    Map<String, Long> metricsMap = metrics().stream().collect(Collectors.toMap(metric -> metric.getName(), metric -> metric.getValue().longValue()));

    return metricsMap;
  }

  private void addBasicMetrics(Collection<Metric<?>> result) {
    Runtime runtime = Runtime.getRuntime();
    result.add(new Metric<>("mem", (runtime.totalMemory() + getTotalNonHeapMemoryIfPossible()) / 1024));
    result.add(new Metric<>("mem.free", runtime.freeMemory() / 1024));
    result.add(new Metric<>("processors", runtime.availableProcessors()));
    result.add(new Metric<>("instance.uptime", System.currentTimeMillis() - this.timestamp));
  }

  private long getTotalNonHeapMemoryIfPossible() {
    try {
      return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }
    catch (Throwable ex) {
      return 0;
    }
  }


  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE + 10;
  }


}
