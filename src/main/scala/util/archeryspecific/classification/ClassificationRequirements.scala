package util.archeryspecific.classification

import util.archeryspecific.classification.Classifications._

import scala.beans.BeanProperty

/**
  * Project: Archery_Records
  * Package: util.archeryspecific
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
  A class that stores all of the data relating to the score needed to gain a classification for each round/category
 */

/*
  http://www.archerygb.org/downloads/20111024ProposedClassificationtables2012.pdf

  object Gender:
    object AgeGroup:
      round1: Map[String, Int]
      round2: Map
      ...
    object AgeGroup2:
      rounds....
    //TODO intialise all round names with classifications for each category
 */
object ClassificationRequirements {

  object Male{
    object Adult extends RoundNames{
      york = Map[String, Int](
        GRAND_MASTER_BOWMAN -> 1146,
        MASTER_BOWMAN -> 1065,
        BOWMAN -> 913,
        FIRST_CLASS -> 698,
        SECOND_CLASS -> 511,
        THIRD_CLASS -> 283
      )
    }

    object Under18 extends RoundNames{

    }

    object Under16 extends RoundNames{

    }

    object  Under14 extends RoundNames{

    }

    object Under12 extends RoundNames{

    }

  }

  object Female{
    object Adult extends RoundNames{

    }

    object Under18 extends RoundNames{

    }

    object Under16 extends RoundNames{

    }

    object  Under14 extends RoundNames{

    }

    object Under12 extends RoundNames{

    }

  }

  def calculateHandicap(roundName: String, score: Int): Int = {
    0
  }

  def calculateClassfication(roundName: String, score: Int): String = {
    "NYI"
  }
}

abstract class RoundNames{
  //Indoor and outdoor rounds, setup beans properties to be initialised in parent objects
  @BeanProperty var york: Map[String, Int] = _
  @BeanProperty var hereford: Map[String, Int] = _
  @BeanProperty var bristol1: Map[String, Int] = _
  @BeanProperty var bristol2: Map[String, Int] = _
  @BeanProperty var bristol3: Map[String, Int] = _
  @BeanProperty var bristol4: Map[String, Int] = _
  @BeanProperty var bristol5: Map[String, Int] = _
  @BeanProperty var stGeorge: Map[String, Int] = _
  @BeanProperty var albion: Map[String, Int] = _
  @BeanProperty var windsor: Map[String, Int] = _
  @BeanProperty var shortWindsor: Map[String, Int] = _
  @BeanProperty var juniorWindsor: Map[String, Int] = _
  @BeanProperty var shortJuniorWindsor: Map[String, Int] = _

  //TODO add all rounds

  val allOutdoorImperialRounds: List[String] = List(
    "york",
    "hereford",
    "bristol2",
    "bristol3",
    "bristol4",
    "bristol5",
    "stgeorge",
    "albion",
    "windsor",
    "shortwindsor",
    "juniorwindsor",
    "shortjuniorwindsor",
    "newwestern",
    "longwestern",
    "western",
    "shortwestern",
    "newwestern",
    "longwestern",
    "western",
    "shortwestern",
    "juniorwestern",
    "shortjuniorwestern",
    "american",
    "stnicolas",
    "newnational",
    "longnational",
    "national",
    "shortnational",
    "juniornational",
    "shortjuniornational",
    "newwarwick",
    "warwick",
    "shortwarwick",
    "juniorwarwick",
    "shortjuniorwarwick"
  )

  val allOutdoorMetricRounds: List[String] = List(
    "fitagents",
    "fitaladies",
    "metric1",
    "metrictwo",
    "metric3",
    "metric4",
    "metric5",
    "longmetric",
    "longmetric1",
    "longmetric2",
    "longmetric3",
    "longmetric4",
    "longmetric5",
    "shortmetric",
    "shortmetric1",
    "shortmetric2",
    "shortmetric3",
    "shortmetric4",
    "shortmetric5",
    "halffita",
    "halfmetric1",
    "halfmetric2",
    "halfmetric3",
    "halfmetric4",
    "halfmetric5",
    "fitastandardbow",
    "olympic",
    "fita900",
    "fita720"
  )

  val allIndoorRounds: List[String] = List(
    "bray1",
    "bray2",
    "stafford",
    "portsmouth",
    "worcester",
    "vegas",
    "fita18",
    "fita25",
    "combinedfita",
    "matchround1"
  )

  val allRounds: List[String] = allOutdoorMetricRounds ++ allOutdoorImperialRounds ++ allIndoorRounds
}

object test{
  ClassificationRequirements.Male.Adult.getAlbion
}