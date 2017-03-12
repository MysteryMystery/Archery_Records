package scene.form.login

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.scene.text.Text

/**
  * Created by James on 12/03/2017.
  */
class NormalLogin(dbh: DatabaseHandler, cfl: ConfigLoader) extends GUIUtil with ScriptingUtil{
  override var databaseHandler: DatabaseHandler = dbh
  override var confLoader: ConfigLoader = cfl
  override var sceneTitle: Text = new Text("User Login")
}
