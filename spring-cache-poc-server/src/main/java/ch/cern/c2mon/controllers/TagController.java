package ch.cern.c2mon.controllers;

import java.util.concurrent.atomic.AtomicLong;

import ch.cern.c2mon.models.Tag;
import ch.cern.c2mon.repositories.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

  AtomicLong atomicLong = new AtomicLong();

  @Autowired
  private TagRepository repository;

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Tag> pushTag(@RequestBody Tag tag) {
    Tag tagWithId = new Tag(atomicLong.incrementAndGet());

    tagWithId.setParams(tag);

    repository.update(tagWithId);

    return new ResponseEntity<>(tag, HttpStatus.OK);
  }
}
