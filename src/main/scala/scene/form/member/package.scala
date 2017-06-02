package scene.form

import javafx.event.ActionEvent

import scene.form.mainmenu.MainMenu

import scalafx.collections.ObservableBuffer
import scalafx.scene.control._
import scalafx.scene.text.Text

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */

/*"id" -> "integer PRIMARY KEY AUTOINCREMENT",
"forename" -> "varchar(255)",
"surname" -> "varchar(255)",
"housenumber" -> "varchar(20)",
"postcode" -> "varchar(7)",
"indoorclassification" -> "varchar(1)",
"outdoorclassification" -> "varchar(16)"*/

package object member {
  //Headers
  val idLabel: Label = new Label("ID: ")
  val forenameLabel: Label = new Label("Forename: ")
  val surnameLabel: Label = new Label("Surname: ")
  val houseNumberLabel: Label = new Label("House Number: ")
  val postcodeLabel: Label = new Label("PostCode: ")
  val indoorClassLabel: Label = new Label("Indoor\nClassification: ")
  val outdoorClassLabel: Label = new Label("Outdoor\nClassification: ")
  val categoryLabel: Label = new Label("Category: ")
  val warningText: Text = new Text(""){
    id = "warning"
  }

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
  val houseNumberEntry: TextField = new TextField(){
    promptText = "12"
  }
  val postCodeEntry: TextField = new TextField(){
    promptText = "LL99 2GQ"
  }
  val indoorClassEntry: TextField = new TextField(){
    promptText = "F"
  }
  val outdoorClassEntry: TextField = new TextField(){
    promptText = "Master Bowman"
  }
  val category: ListView[String] = new ListView[String](){
    items = ObservableBuffer[String]("Male Adult", "Female Adult", "Male U18", "Female U18", "Male U16", "Female U16", "Male U12", "Female U12")
    maxHeight = 20.0
  }

  //Buttons
  val back: Button = new Button("Back"){
    onAction = (event: ActionEvent) => {
      ArcheryRecords.primaryStage.scene = new MainMenu getScene
    }
  }

  val submit: Button = new Button("Submit"){
    defaultButton = true
    onAction = (event: ActionEvent) => {
      println(event.getTarget)
    }
  }
}
