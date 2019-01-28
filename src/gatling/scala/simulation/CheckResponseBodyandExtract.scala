package simulation

import BaseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CheckResponseBodyandExtract extends BaseSimulation{

  val scn = scenario("VideoGameDB")

    .exec(http("Get Specific Game - 1st call")
    .get("videogames/1")
    .check(jsonPath("$.name").is("Resident Evil 4").saveAs("gameName")))

    .exec(http("Get All Games")
    .get("videogames")
    .check(jsonPath("$[1].id").saveAs("gameId")))
    .exec {session => println(session); session}

    .exec(http("Get Specific Call - 2nd call")
    .get("videogames/${gameId}")
    .check(jsonPath("$.name").is("Gran Turismo 3"))
      .check(bodyString.saveAs("responseBody")))
      .exec {session => println(session("responseBody").as[String]); session}

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
