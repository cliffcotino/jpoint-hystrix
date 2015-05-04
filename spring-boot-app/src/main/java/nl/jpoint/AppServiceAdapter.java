package nl.jpoint;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import retrofit.RestAdapter;

/**
 *
 */
@Component
public class AppServiceAdapter {

    @HystrixCommand(fallbackMethod = "defaultResponse")
    public String execute() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://localhost:8090").build();
        RestService restService = restAdapter.create(RestService.class);
        return restService.timeout();
    }

    protected String defaultResponse() {
        return "fallbackResponse";
    }
}
