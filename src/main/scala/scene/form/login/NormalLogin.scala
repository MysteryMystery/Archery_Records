package scene.form.login

import scalafx.Includes._

import data.{ConfigLoader, DatabaseHandler}
import scene.form.mainmenu.MainMenu
import util.{GUIUtil, ScriptingUtil}

import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control._
import scalafx.scene.text.Text

/**
  * Created by James on 12/03/2017.
  */
class NormalLogin extends GUIUtil with ScriptingUtil{
  //override var databaseHandler: DatabaseHandler = dbh
  //override var confLoader: ConfigLoader = cfl
  override var sceneTitle: Text = new Text("User Login")

  var usernameLabel: Label = new Label("Username: ")
  var usernameEntry: TextField = new TextField()

  var passwordLabel: Label = new Label("Password: ")
  var passwordEntryPlainText: TextField = new TextField()
  var passwordEntryPasswordField: PasswordField = new PasswordField()
  passwordEntryPlainText.visible = false

  var changePassBox: CheckBox = new CheckBox("Show passwords in plain text.")
  changePassBox.onAction = (event: ActionEvent) => {
    passwordEntryPlainText.visible = changePassBox.selected.get()
    passwordEntryPasswordField.visible = !changePassBox.selected.get()

    if (changePassBox.selected.get()){
      passwordEntryPlainText.setText(passwordEntryPasswordField.getText)
    }else{
      passwordEntryPasswordField.setText(passwordEntryPlainText.getText)
    }
  }

  var loginButton: Button = new Button("Login")
  loginButton.defaultButton = true
  loginButton.onAction = (event: ActionEvent) => {
    var text: String = null
    if (passwordEntryPlainText.isVisible){
      text = passwordEntryPlainText.getText
    }else{
      text = passwordEntryPasswordField.getText
    }

    if (databaseHandler.getAccount(usernameEntry.getText(), hash(text)).next()){
      //Continue to next scene (main menu)
      ArcheryRecords.primaryStage.scene = (new MainMenu).getScene
    }else{
      warningText.text = scene.incorrectPassUserCombination
    }
  }

  var warningText:Text = new Text("")
  warningText.id = "warning"

  //addInCol(0, 1, 1, 1, usernameLabel, passwordLabel)
  //addInCol(1, 1, 2, 1, usernameEntry, passwordEntryPasswordField)
  gridPane.add(usernameLabel, 0, 1, 1, 1)
  gridPane.add(passwordLabel, 0, 2, 1, 1)
  gridPane.add(usernameEntry, 1, 1, 2, 1)
  gridPane.add(passwordEntryPasswordField, 1, 2, 2, 1)
  gridPane.add(passwordEntryPlainText, 1, 2, 2, 1)
  gridPane.add(loginButton, 2, 3)
  gridPane.add(changePassBox, 0, 3, 2, 1)
  gridPane.add(warningText, 0, 4, 3, 1)
}
