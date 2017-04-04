package ch.cern.c2mon.repositories;

import ch.cern.c2mon.models.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author Szymon Halastra
 */
@Slf4j
@Component
@CacheConfig(cacheNames = "tags")
public class TagRepository {

  @Cacheable
  public Tag find(long id) {
    return new Tag(0);
  }

  @CachePut
  public Tag update(Tag tag) {
    log.info("Tag {} was updated", tag.toString());

    return tag;
  }

  @CacheEvict
  public void delete(Tag tag) {
    log.info("Tag {} was deleted", tag.toString());
  }
}
