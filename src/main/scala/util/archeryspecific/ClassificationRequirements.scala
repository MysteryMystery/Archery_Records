package util.archeryspecific

import scala.beans.BeanProperty
import Classifications._

/**
  * Project: Archery_Records
  * Package: util.archeryspecific
  * Created by MysteryMystery under the MIT Liscense.
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

 */
object ClassificationRequirements {

  object Male{
    object Adult extends Commons{
      york = Map[String, Int](
        getGrandMasterBowman -> 1146,
        getMasterBowman -> 1065,
        getBowman -> 913,
        getFirstClass -> 698,
        getSecondClass -> 511,
        getThirdClass -> 283
      )
    }

    object Under18 extends Commons{

    }

    object Under16 extends Commons{

    }

    object  Under14 extends Commons{

    }

    object Under12 extends Commons{

    }

  }

  object Female{
    object Adult extends Commons{

    }

    object Under18 extends Commons{

    }

    object Under16 extends Commons{

    }

    object  Under14 extends Commons{

    }

    object Under12 extends Commons{

    }

  }
}

abstract class Commons{
  @BeanProperty var york: Map[String, Int] = _
  @BeanProperty var hereford: Map[String, Int] = _
  @BeanProperty var bristol1: Map[String, Int] = _
  @BeanProperty var bristol2: Map[String, Int] = _
  @BeanProperty var bristol3: Map[String, Int] = _
  @BeanProperty var bristol4: Map[String, Int] = _
  @BeanProperty var bristol5: Map[String, Int] = _
  @BeanProperty var stGeorge: Map[String, Int] = _
  @BeanProperty var albion: Map[String, Int] = _
  @BeanProperty var windor: Map[String, Int] = _
  @BeanProperty var shortWindsor: Map[String, Int] = _
  @BeanProperty var juniorWindsor: Map[String, Int] = _
  @BeanProperty var shortJuniorWindsor: Map[String, Int] = _
}

object test{
  ClassificationRequirements.Male.Adult.getAlbion
}