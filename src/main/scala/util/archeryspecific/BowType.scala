package util.archeryspecific

/**
  * Project: Archery_Records
  * Package: util.archeryspecific
  * Created by MysteryMystery under the MIT Liscense.
  */
abstract class BowType {
  val identifier: String
  val displayName: String
}

object Recurve extends BowType{
  override val identifier: String = "R"
  override val displayName: String = "Recurve"
}

object Longbow extends BowType{
  override val identifier: String = "L"
  override val displayName: String = "Longbow"
}

object Compound extends BowType{
  override val identifier: String = "C"
  override val displayName: String = "Compound"
}
