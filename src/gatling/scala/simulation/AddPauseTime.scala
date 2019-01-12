package simulation
import BaseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt
class AddPauseTime extends BaseSimulation{

  val scn = scenario("Video Game DB")
    .exec(http("Get All Games - 1st Call")
    .get("videogames"))
    .pause(3)

    .exec(http("Get Specific Game")
    .get("videogames/1"))
    .pause(1,20)

    .exec(http("Get All Games - 2nd call")
    .get("videogames"))
    .pause(3000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpConf)
  )

}
