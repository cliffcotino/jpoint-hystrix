package nl.jpoint;

import com.github.rjeschke.txtmark.Processor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@ResponseBody
@RestController
public class Controller {

    private int maxTimeout = 1000;
    private Random random = new Random();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    JsonResponse sayHello(@RequestParam(value = "value", defaultValue = "N/A") String value) {
        try {
            int sleepTime = random.nextInt(maxTimeout);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e);
        }
        return new JsonResponse(atomicInteger.get(), Processor.process(value));
    }

    @RequestMapping(value = "/timeout", produces = MediaType.APPLICATION_JSON_VALUE)
    JsonResponse setTimeout(@RequestParam(value = "value", required = false) Integer timeout) {
        if (timeout == null) {
            return new JsonResponse(atomicInteger.get(), "Timeout is: " + maxTimeout);
        } else {
            maxTimeout = timeout;
            return new JsonResponse(atomicInteger.incrementAndGet(), "Timeout set to " + maxTimeout);
        }
    }

}
