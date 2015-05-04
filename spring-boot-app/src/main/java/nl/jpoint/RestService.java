package nl.jpoint;

import retrofit.http.GET;

/**
 *
 */
public interface RestService {

    @GET("/")
    String timeout();
}
