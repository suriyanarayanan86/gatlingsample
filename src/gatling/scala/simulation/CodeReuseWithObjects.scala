package simulation
import BaseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CodeReuseWithObjects extends BaseSimulation {

  def getAllVideoGames() = {
    repeat(3){
      exec(http("Get All Video Games")
        .get("videogames")
        .check(status.in(200 to 210)))
    }
  }

  def getSpecificVideoGames() = {
    repeat(6){
      exec(http("Get Specific Video Game")
      .get("videogames/1")
      .check(status.in(200 to 210)))
    }
  }

  val scn = scenario("Video Game DB")
      .exec(getAllVideoGames())
      .pause(5)
      .exec(getSpecificVideoGames())
      .pause(5)
      .exec(getAllVideoGames())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
