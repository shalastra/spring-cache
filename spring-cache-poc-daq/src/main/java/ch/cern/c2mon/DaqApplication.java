package ch.cern.c2mon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Szymon Halastra
 */

@SpringBootApplication
public class DaqApplication {

  public static void main(String[] args) {
    SpringApplication.run(DaqApplication.class, args);
  }
}
