package scene.form.mainmenu

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.event.ActionEvent
import scalafx.scene.control.Button
import scalafx.scene.text.Text
import ArcheryRecords._
import scene.form.member.MemberMenu

/**
  * Project: Archery_Records
  * Package: scene.form.mainmenu
  * Created by MysteryMystery under the MIT Liscense.
  */
class MainMenu extends GUIUtil with ScriptingUtil {
  override var sceneTitle: Text = new Text("Main Menu")

  gridPane.add(new Button("Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      primaryStage.scene_=((new MemberMenu).getScene)
    }
  }, 1, 1)
}
