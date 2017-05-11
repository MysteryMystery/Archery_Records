package scene.form.member

import util.GUIUtil

import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.Text
import util.ImplicitHelpers._

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */
class AddMember extends GUIUtil{
  override protected var sceneTitle: Text = new Text("Add Member")

  submit.onAction = (event: javafx.event.ActionEvent) => {
    if (forenameEntry.text.value == "" || surnameEntry.text.value == ""){
      warningText.text = "You need to name the archer!"
    }
    else if (!postCodeEntry.text.value.isPostCode()){
      warningText.text = "The postcode is not in correct format!"
    }
    else {
      databaseHandler.insertArcher(
        forenameEntry.text.value,
        surnameEntry.text.value,
        houseNumberEntry.text.value,
        postCodeEntry.text.value,
        indoorClassEntry.text.value,
        outdoorClassEntry.text.value
      )
      back.fire()
    }
  }

  addInCol(0, 1, 1, 1,
    forenameLabel,
    surnameLabel,
    houseNumberLabel,
    postcodeLabel,
    indoorClassLabel,
    outdoorClassLabel,
    back
  )

  addInCol(1, 1, 1, 1,
    forenameEntry,
    surnameEntry,
    houseNumberEntry,
    postCodeEntry,
    indoorClassEntry,
    outdoorClassEntry,
    submit
  )

  gridPane.add(warningText, 0, 8, 2, 1)
}
