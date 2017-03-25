package scene.form.mainmenu

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.scene.text.Text

/**
  * Project: Archery_Records
  * Package: scene.form.mainmenu
  * Created by MysteryMystery under the MIT Liscense.
  */
class MainMenu extends GUIUtil with ScriptingUtil {
  override var sceneTitle: Text = new Text("Main Menu")
}
