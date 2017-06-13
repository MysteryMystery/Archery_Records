package scene.form.login


import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scala.collection.mutable.ListBuffer
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{GridPane, HBox, VBox}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.{ActionEvent, Event}

/**
  * Created by James on 08/03/2017.
  */
class FirstUserRegistration extends GUIUtil with ScriptingUtil{
  override var sceneTitle: Text = new Text("First Time Login")
  //override var databaseHandler: DatabaseHandler = dbh
  //override var confLoader: ConfigLoader = configLoader

  var userLabel: Label = new Label("New Username: ")
  var passLabel: Label = new Label("New Password: ")
  var passLabelCheck: Label = new Label("Re-enter Password: ")

  var userEntry: TextField = new TextField()
  var passEntryPlainText: TextField = new TextField()
  var passEntryCheck: TextField = new TextField()
  passEntryCheck.visible = false
  passEntryPlainText.visible = false

  var passEntryPass: PasswordField = new PasswordField()
  var passEntryPassCheck: PasswordField = new PasswordField()

  var warningField: Text = new Text("")
  warningField.setId("warning")

  var registerButton:Button = new Button("Register") {
    onAction = (event: Event) => {
      //Check if combination valid etc THEN
      // store THEN
      // change scene to main menu
      registerButtonFunc(event)
    }
  }
  registerButton.setDefaultButton(true)

  var changePassBox: CheckBox = new CheckBox("Show passwords in plain text.")
  changePassBox.onAction = (event: ActionEvent) => {
    passEntryCheck.visible = changePassBox.selected.get()
    passEntryPlainText.visible = changePassBox.selected.get()

    passEntryPass.visible = !changePassBox.selected.get()
    passEntryPassCheck.visible = !changePassBox.selected.get()

    if (changePassBox.selected.get()){
      passEntryPlainText.setText(passEntryPass.getText)
      passEntryCheck.setText(passEntryPassCheck.getText)
    }else{
      passEntryPass.setText(passEntryPlainText.getText)
      passEntryPassCheck.setText(passEntryCheck.getText)
    }
  }

  //visibility - make into method
  passEntryPlainText.visible = false
  passEntryCheck.visible = false

  gridPane.add(userLabel, 0, 1, 1, 1)
  gridPane.add(passLabel, 0, 2, 1, 1)
  gridPane.add(passLabelCheck, 0, 3, 1, 1)
  gridPane.add(changePassBox, 0, 4, 2, 1)
  gridPane.add(warningField, 0, 5, 4, 1)

  gridPane.add(userEntry, 1, 1, 2, 1)
  gridPane.add(passEntryPass, 1, 2, 2, 1)
  gridPane.add(passEntryPassCheck, 1, 3, 2, 1)
  gridPane.add(passEntryPlainText, 1, 2, 2, 1)
  gridPane.add(passEntryCheck, 1, 3, 2, 1)

  gridPane.add(registerButton, 2, 4, 1, 1)

  makeMaxWidth(userLabel, passLabel, passLabelCheck)

  def registerButtonFunc(event: Event): Unit = {
    var pswds: ListBuffer[String] = ListBuffer()

    if (passEntryPlainText.visible.apply()){
      pswds += passEntryPlainText.getText
      pswds += passEntryCheck.getText()
    }else{
      pswds += passEntryPass.getText()
      pswds += passEntryPassCheck.getText()
    }

    if (pswds.head == pswds(1)){
      databaseHandler.insert("account", Map(
        "username" -> userEntry.getText,
        "password" -> hash(pswds.head)
      ))

      ArcheryRecords.primaryStage.scene = new NormalLogin().getScene
    }
    else{
      warningField.setText("Please check that the passwords are matching.")
    }
  }
}
