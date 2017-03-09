package scene.form.login


import data.DatabaseHandler
import util.{GUIUtil, ScriptingUtil}

import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, PasswordField, TextField}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{GridPane, HBox, VBox}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.Event

/**
  * Created by James on 08/03/2017.
  */
class FirstUserRegistration() extends GUIUtil with ScriptingUtil{
  override var sceneTitle: Text = new Text("First Time Login")

  var userLabel: Label = new Label("New Username: ")
  var passLabel: Label = new Label("New Password: ")
  var passLabelCheck: Label = new Label("Re-enter Password: ")

  var userEntry: TextField = new TextField()
  var passEntryPlainText: TextField = new TextField()
  var passEntryCheck: TextField = new TextField()

  var passEntryPass: PasswordField = new PasswordField()
  var passEntryPassCheck: PasswordField = new PasswordField()

  var registerButton:Button = new Button("Register") {
    onAction = (event: Event) => {
      //Check if combination valid etc THEN
      // store THEN
      // change scene to main menu
      println("Button Clicked")
    }
  }
  registerButton.setDefaultButton(true)

  //positioning

  gridPane.add(userLabel, 0, 1, 1, 1)
  gridPane.add(passLabel, 0, 2, 1, 1)
  gridPane.add(passLabelCheck, 0, 3, 1, 1)

  gridPane.add(userEntry, 1, 1, 2, 1)
  gridPane.add(passEntryPass, 1, 2, 2, 1)
  gridPane.add(passEntryPassCheck, 1, 3, 2, 1)

  gridPane.add(registerButton, 2, 4, 1, 1)

  makeMaxWidth(userLabel, passLabel, passLabelCheck)

  def handle(event: Event): Unit ={

  }
}
