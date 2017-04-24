package scene.form

import javafx.event.ActionEvent

import scene.form.mainmenu.MainMenu

import scalafx.scene.control.{Button, Label, TextField}

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */
package object member {
  //Headers
  val idLabel: Label = new Label("ID: ")
  val forenameLabel: Label = new Label("Forename: ")
  val surnameLabel: Label = new Label("Surname: ")
  val handicapLabel: Label = new Label("Initial Handicap: ")

  //Entries
  val forenameEntry: TextField = new TextField(){
    promptText = "John"
  }
  val surnameEntry: TextField = new TextField() {
    promptText = "Smith"
  }
  val handicapEntry: TextField = new TextField(){
    promptText = "6"
  }

  //Buttons
  val back: Button = new Button("Back"){
    onAction = (event: ActionEvent) => {
      ArcheryRecords.primaryStage.scene = new MainMenu getScene
    }
  }

  val submit: Button = new Button("Submit"){
    onAction = (event: ActionEvent) => {
      println(event.getTarget)
    }
  }
}
