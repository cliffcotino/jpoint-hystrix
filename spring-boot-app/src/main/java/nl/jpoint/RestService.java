package nl.jpoint;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 *
 */
public interface RestService {

    @GET("/")
    Markdown markdown(@Query("value") String value);
}
