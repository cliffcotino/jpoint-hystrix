package nl.jpoint;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.rjeschke.txtmark.Processor;

/**
 *
 */
@ResponseBody
@RestController
public class Controller {

    private int maxTimeout = 1000;
    private Random random = new Random();

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    String sayHello(@RequestParam(value = "value", defaultValue = "N/A") String value) {
        try {
            int sleepTime = random.nextInt(maxTimeout);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e);
        }
        return String.format("{ response: \"%s\" }", Processor.process(value));
    }

    @RequestMapping(value = "/timeout", produces = MediaType.APPLICATION_JSON_VALUE)
    String setTimeout(@RequestParam(value = "value", required = false) Integer timeout) {
        if (timeout == null) {
            return "Timeout is: " + maxTimeout;
        } else {
            maxTimeout = timeout;
            return String.format("{ response: \"Timeout set to %s\" }", maxTimeout);
        }
    }

}
