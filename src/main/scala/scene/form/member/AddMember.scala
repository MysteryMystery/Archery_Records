package scene.form.member

import util.GUIUtil

import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.Text
import util.ImplicitHelpers._

import scalafx.Includes._
import scalafx.event.ActionEvent

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */
class AddMember extends GUIUtil{
  override protected var sceneTitle: Text = new Text("Add Member")
  indoorClassLabel.text = "Initial Indoor\nClassification: "
  outdoorClassLabel.text = "Initial Outdoor\nClassification: "

  submit.onAction = (event: ActionEvent) => {
    if (forenameEntry.text.value == "" || surnameEntry.text.value == ""){
      warningText.text = "You need to name the archer!"
    }
    else if (!postCodeEntry.text.value.isPostCode()){
      warningText.text = "The postcode is not in correct format!"
    }
    else if (category.getSelectionModel.getSelectedItem == null){
      warningText.text = "Please select an age group!"
    }
    else {
      databaseHandler.insertArcher(
        forenameEntry.text.value,
        surnameEntry.text.value,
        houseNumberEntry.text.value,
        postCodeEntry.text.value,
        indoorClassEntry.text.value,
        outdoorClassEntry.text.value,
        category.getSelectionModel.getSelectedItem
      )
      back.fire()
    }
  }

  addInCol(0, 1, 1, 1,
    forenameLabel,
    surnameLabel,
    houseNumberLabel,
    postcodeLabel,
    categoryLabel,
    indoorClassLabel,
    outdoorClassLabel,
    back
  )

  addInCol(1, 1, 1, 1,
    forenameEntry,
    surnameEntry,
    houseNumberEntry,
    postCodeEntry,
    category,
    indoorClassEntry,
    outdoorClassEntry,
    submit
  )

  gridPane.add(warningText, 0, 8, 2, 1)
}
