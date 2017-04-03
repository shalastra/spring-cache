package ch.cern.c2mon.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Szymon Halastra
 */
@Data
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Serializable {

  private long id;

  private long memory;
  private long freeMemory;
  private int processors;

  private long instanceUptime;

  public Tag() {

  }

  public Tag(long id) {
    this.id = id;
  }

  public Tag(long memory, long freeMemory, int processors, long instanceUptime) {
    this.memory = memory;
    this.freeMemory = freeMemory;
    this.processors = processors;
    this.instanceUptime = instanceUptime;
  }

  public void setParams(final Tag tag) {
    this.memory = tag.getMemory();
    this.freeMemory = tag.getFreeMemory();
    this.processors = tag.getProcessors();
    this.instanceUptime = tag.getInstanceUptime();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag = (Tag) o;

    if (id != tag.id) return false;
    if (memory != tag.memory) return false;
    if (freeMemory != tag.freeMemory) return false;
    if (processors != tag.processors) return false;
    return instanceUptime == tag.instanceUptime;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (int) (memory ^ (memory >>> 32));
    result = 31 * result + (int) (freeMemory ^ (freeMemory >>> 32));
    result = 31 * result + processors;
    result = 31 * result + (int) (instanceUptime ^ (instanceUptime >>> 32));
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Tag{");
    sb.append("id=").append(id);
    sb.append(", memory=").append(memory);
    sb.append(", freeMemory=").append(freeMemory);
    sb.append(", processors=").append(processors);
    sb.append(", instanceUptime=").append(instanceUptime);
    sb.append('}');
    return sb.toString();
  }
}