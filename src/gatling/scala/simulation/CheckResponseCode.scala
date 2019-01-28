package simulation
import BaseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CheckResponseCode extends BaseSimulation{

  val scn = scenario("VideoGameDB")
    .exec(http("Get All Games - 1st call")
        .get("videogames")
        .check(status.is(400)))

    .exec(http("Get Specific Game")
        .get("videogames/1")
        .check(status.in(200 to 210)))

    .exec(http("Get All Games - 2nd Call")
        .get("videogames")
        .check(status.not(400), status.not(404), status.not(500)))

    setUp(
      scn.inject(atOnceUsers(1))
    ).protocols(httpConf)

}
