// package computerdatabase

import java.util.Random
import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    // .baseURL("http://localhost:8091/")
    .acceptHeader("application/json")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val users = scenario("Users")
    .repeat(5, "n") {  // get the message a number of times
     exec(
       http("request_app_1").get("http://localhost:8091/markdown").queryParam("value", UUID.randomUUID().toString))
       .pause(1)
  }

  val chaosChimp = scenario("ChaosChimp")
    .exec(http("request_set_timeout").get("http://localhost:8090/timeout").queryParam("value", new Random().nextInt(10000))) // set the timeout

  val chaosGorilla = scenario("ChaosGorilla")
    .exec(http("request_set_toggle").get("http://localhost:8090/toggle")) // toggle the service

  setUp(
    chaosChimp.inject(rampUsers(500) over (30 seconds))
    , users.inject(rampUsers(50) over (30 seconds))
    , chaosGorilla.inject(rampUsers(25) over (30 seconds))
  ).protocols(httpConf)
}