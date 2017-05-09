package scene.form.member

import util.GUIUtil

import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.Text

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */
class AddMember extends GUIUtil{
  override protected var sceneTitle: Text = new Text("Add Member")

  addInCol(0, 1, 1, 1,
    forenameLabel,
    surnameLabel,
    handicapLabel
  )
}
