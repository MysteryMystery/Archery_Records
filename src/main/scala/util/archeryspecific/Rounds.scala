package util.archeryspecific

import scala.collection.mutable

/**
  * Project: Archery_Records
  * Package: util.archeryspecific
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
  Objects and classes that stores information about the number of arrows shot each end (doz.)
  http://www.hertsarchery.org.uk/pdfs/RSHC.pdf
 */

object Rounds {
  val ADULT: String = "Adult"
  val UNDER18: String = "U18"
  val UNDER16: String = "U16"
  val UNDER14: String = "U14"
  val UNDER12: String = "U12"

  //Either convert into hashmap or individual variables due to accessibility
  val rounds: List[Object] = List(
    new ImperialRound("York",        6, 4, 2, 0, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Hereford",    0, 6, 4, 2, 0, 0, 0, 0 , UNDER18, ADULT),
    new ImperialRound("Bristol II",  0, 0, 6, 4, 2, 0, 0, 0, UNDER16, UNDER18),
    new ImperialRound("Bristol III", 0, 0, 0, 6, 4, 2, 0, 0, UNDER14, UNDER16),
    new ImperialRound("Bristol IV",  0, 0, 0, 0, 6, 4, 2, 0, UNDER12, UNDER14),
    new ImperialRound("Bristol V",   0, 0, 0, 0, 0, 6, 4, 2, null, UNDER12),

    new ImperialRound("St. George",           3, 3, 3, 0, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Albion",               0, 3, 3, 3, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Windsor",              0, 0, 3, 3, 3, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Short Windsor",        0, 0, 0, 3, 3, 3, 0, 0, UNDER16, UNDER18),
    new ImperialRound("Junior Windsor",       0, 0, 0, 0, 3, 3, 3, 0, UNDER14, UNDER16),
    new ImperialRound("Short Junior Windsor", 0, 0, 0, 0, 0, 3, 3, 3, null, UNDER12),

    new ImperialRound("New Western",          4, 4, 0, 0, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Long Western",         0, 4, 4, 0, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Western",              0, 0, 4, 4, 0, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("Short Western",        0, 0, 0, 4, 4, 0, 0, 0, UNDER16, UNDER18),
    new ImperialRound("Junior Western",       0, 0, 0, 0, 4, 4, 0, 0, UNDER14, UNDER16),
    new ImperialRound("Short Junior Western", 0, 0, 0, 0, 0, 4, 4, 0, UNDER12, UNDER14),

    new ImperialRound("American", 0, 0, 2.5, 2.5, 2.5, 0, 0, 0, ADULT, ADULT),
    new ImperialRound("St. Nicholas", 0, 0, 0, 0, 4, 3, 0, 0, UNDER14, UNDER16)
  )
}

class ImperialRound(n: String, Hundred: Double, Eighty: Double, Sixty: Double, Fifty: Double, Forty: Double, Thirty: Double, Twenty: Double, Ten: Double, Gents: String, Ladies: String) {
  val name: String = n
  val hundred: Double = hundred
  val eighty: Double = eighty
  val sixty: Double = sixty
  val fifty: Double = fifty
  val forty: Double = forty
  val thirty: Double = thirty
  val twenty: Double = twenty
  val ten: Double = ten
  val total: Double = hundred + eighty + sixty + fifty + forty + thirty + twenty + ten
  val gents: String = gents
  val ladies: String = ladies
}

class MetricRound(Name: String, _122f90: Double, _122f70: Double, _122f60: Double, _122f50: Double, _122f40:Double, _122f30: Double, _122f20: Double,
                  _80f50: Double,  _80f40: Double,  _80f30: Double,  _80f20: Double,  _80f15: Double,  _80f10: Double, Gents: String, Ladies: String){
  val name: String = Name
  object face_122cm{
    val ninety: Double = _122f90
    val seventy: Double = _122f70
    val sixty: Double = _122f60
    val fifty: Double = _122f50
    val forty: Double = _122f40
    val thirty: Double = _122f30
    val twenty: Double = _122f20
  }
  object face_80cm{
    val fifty: Double = _80f50
    val forty: Double = _80f40
    val thirty: Double = _80f30
    val twenty: Double = _80f20
    val fifteen: Double = _80f15
    val ten: Double = _80f10
  }
}

class IndoorRound(Name: String, _40f18m: Double, _40sf18m: Double, _60f25m: Double, _80f30m: Double, _60f20y: Double, _40f20y: Double, _60f25y: Double, _16insf20y: Double){
  object face_40cm{

  }
  object face_40cm_special{

  }
  object face_60cm{

  }
  object face_80cm{

  }
  object face_16in_special{

  }
}