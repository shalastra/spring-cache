package ch.cern.c2mon.models;

import java.io.Serializable;
import java.util.Map;

import ch.cern.c2mon.metrics.SystemMetrics;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Szymon Halastra
 */
@Data
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Serializable {

  private long memory;
  private long freeMemory;
  private int processors;

  private long instanceUptime;

  public Tag() {
    this.updateTag();
  }

  public Tag updateTag() {
    SystemMetrics systemMetrics = new SystemMetrics();
    Map<String, Long> metrics = systemMetrics.getMetricsMap();

    this.memory = metrics.get("mem");
    this.freeMemory = metrics.get("mem.free");
    this.processors = metrics.get("processors").intValue();
    this.instanceUptime = metrics.get("instance.uptime");

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag = (Tag) o;

    if (memory != tag.memory) return false;
    if (freeMemory != tag.freeMemory) return false;
    if (processors != tag.processors) return false;
    return instanceUptime == tag.instanceUptime;
  }

  @Override
  public int hashCode() {
    int result = (int) (memory ^ (memory >>> 32));
    result = 31 * result + (int) (freeMemory ^ (freeMemory >>> 32));
    result = 31 * result + processors;
    result = 31 * result + (int) (instanceUptime ^ (instanceUptime >>> 32));
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Tag{");
    sb.append(", memory=").append(memory);
    sb.append(", freeMemory=").append(freeMemory);
    sb.append(", processors=").append(processors);
    sb.append(", instanceUptime=").append(instanceUptime);
    sb.append('}');
    return sb.toString();
  }
}