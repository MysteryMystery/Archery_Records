package scene.form.login

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.event.ActionEvent
import scalafx.scene.control.{CheckBox, Label, PasswordField, TextField}
import scalafx.scene.text.Text

/**
  * Created by James on 12/03/2017.
  */
class NormalLogin(dbh: DatabaseHandler, cfl: ConfigLoader) extends GUIUtil with ScriptingUtil{
  override var databaseHandler: DatabaseHandler = dbh
  override var confLoader: ConfigLoader = cfl
  override var sceneTitle: Text = new Text("User Login")

  var usernameLabel: Label = new Label("Username: ")
  var usernameEntry: TextField = new TextField()

  var passwordLabel: Label = new Label("Password: ")
  var passwordEntryPlainText: TextField = new TextField()
  var passwordEntryPasswordField: PasswordField = new PasswordField()
  passwordEntryPlainText.visible = false

  var changePassBox: CheckBox = new CheckBox("Show passwords in plain text.")
  //changePassBox.onAction =

  addInCol(0, 1, 1, 1, usernameLabel, passwordLabel)
  addInCol(1, 1, 1, 1, usernameEntry, usernameLabel)

  def checkBoxFunc(event: ActionEvent): Unit = {
    passwordEntryPlainText.visible = changePassBox.selected.get()
    passwordEntryPasswordField.visible = !changePassBox.selected.get()

    if (changePassBox.selected.get()){
      passwordEntryPlainText.setText(passwordEntryPasswordField.getText)
    }else{
      passwordEntryPasswordField.setText(passwordEntryPlainText.getText)
    }
  }
}
