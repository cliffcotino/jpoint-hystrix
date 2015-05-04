package nl.jpoint;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import retrofit.RestAdapter;

/**
 *
 */
@Component
public class AppServiceAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceAdapter.class);

    @HystrixCommand(fallbackMethod = "defaultResponse")
    public String execute(String value) {
        try {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://localhost:8090")
                    .build();
            RestService restService = restAdapter.create(RestService.class);

            Markdown markdown = restService.markdown(value);
            return markdown.getContent();
        } catch (Exception e) {
            LOGGER.warn("Failed to markdown", e);
            throw e;
        }
    }

    protected String defaultResponse(String value) {
        return "fallbackResponse";
    }
}
