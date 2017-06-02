package util.archeryspecific.classification

/**
  * Project: Archery_Records
  * Package: util
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
    Companion Object for ClassificationRequirements
 */

object Classifications {
  val THIRD_CLASS: String = "3rd Class"
  val SECOND_CLASS: String = "2nd Class"
  val FIRST_CLASS: String = "1st Class"
  val BOWMAN: String = "Bowman"
  val JUNIOR_BOWMAN: String = "Junior Bowman"
  val MASTER_BOWMAN: String = "Master Bowman"
  val JUNIOR_MASTER_BOWMAN: String = "Junior Master Bowman"
  val GRAND_MASTER_BOWMAN: String = "Grand Master Bowman"

  val JUNIOR_CLASSIFICATIONS = List(THIRD_CLASS, SECOND_CLASS, FIRST_CLASS, JUNIOR_BOWMAN, JUNIOR_MASTER_BOWMAN)
  val CLASSIFICIATIONS = List(THIRD_CLASS, SECOND_CLASS, FIRST_CLASS, BOWMAN, MASTER_BOWMAN, GRAND_MASTER_BOWMAN)
}
