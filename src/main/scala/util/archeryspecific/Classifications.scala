package util.archeryspecific

/**
  * Project: Archery_Records
  * Package: util
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
    Companion Object for ClassificationRequirements
 */

object Classifications {
  private val THIRD_CLASS: String = "3rd Class"
  private val SECOND_CLASS: String = "2nd Class"
  private val FIRST_CLASS: String = "1st Class"
  private val BOWMAN: String = "Bowman"
  private val JUNIOR_BOWMAN: String = "Junior Bowman"
  private val MASTER_BOWMAN: String = "Master Bowman"
  private val JUNIOR_MASTER_BOWMAN: String = "Junior Master Bowman"
  private val GRAND_MASTER_BOWMAN: String = "Grand Master Bowman"

  private val JUNIOR_CLASSIFICATIONS = List(THIRD_CLASS, SECOND_CLASS, FIRST_CLASS, JUNIOR_BOWMAN, JUNIOR_MASTER_BOWMAN)
  private val CLASSIFICIATIONS = List(THIRD_CLASS, SECOND_CLASS, FIRST_CLASS, BOWMAN, MASTER_BOWMAN, GRAND_MASTER_BOWMAN)

  def getThirdClass: String = THIRD_CLASS
  def getSecondClass: String = SECOND_CLASS
  def getFirstClass: String = FIRST_CLASS
  def getBowman: String = BOWMAN
  def getJuniorBowman: String = JUNIOR_BOWMAN
  def getMasterBowman: String = MASTER_BOWMAN
  def getJuniorMasterBowman: String = JUNIOR_MASTER_BOWMAN
  def getGrandMasterBowman: String = GRAND_MASTER_BOWMAN

  def getJuniorClassifications: List[String] = JUNIOR_CLASSIFICATIONS
  def getClassifications: List[String] = CLASSIFICIATIONS
}
