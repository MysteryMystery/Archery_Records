package scene.form.mainmenu

import java.lang.reflect.Field
import javafx.collections.FXCollections

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, ListView, ScrollPane, TableView}
import scalafx.scene.text.Text
import ArcheryRecords._
import scene.form.member.{AddMember, DisplayMember}
import util.personspecific.Member
import util.ImplicitHelpers
import util.archeryspecific.{ImperialRound, Rounds}

import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{Background, GridPane}

/**
  * Project: Archery_Records
  * Package: scene.form.mainmenu
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
class MainMenu extends GUIUtil with ScriptingUtil {
  override var sceneTitle: Text = new Text("Main Menu")
  gridPane.add(new Text("Click the entity which you would like to manage: "), 1, 1, 2, 1)

  gridPane.add(
    new Button("Add Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      primaryStage.scene_=((new AddMember).getScene)
    }
  }, 1, 2)


}
*/

class MainMenu extends GUIUtil with ScriptingUtil {
  override var sceneTitle: Text = new Text("Main Menu")

  var memberListPane: ScrollPane = new ScrollPane()

  var memberListView: ListView[String] = new ListView[String](){
    onMouseClicked = (event: javafx.scene.input.MouseEvent) => {
      if (event.getClickCount == 2){
        val selectedItems = memberListView.getSelectionModel.getSelectedItem.split(" ")
        ArcheryRecords.logger.log(ArcheryRecords.logger.DEBUG, this.getClass.getName, s"Selected Item: ${selectedItems.mkString(" ")}")

        primaryStage.scene = new DisplayMember(databaseHandler.getArcher(selectedItems(0).replace(".", ""), selectedItems(1), selectedItems(2), null, null, null, null)) getScene
      }
    }

  }
  var roundListPane: ScrollPane = new ScrollPane()
  var roundListView: ListView[String] = new ListView[String]()

  var buttonsGrid: GridPane = new GridPane(){
    alignment = Pos.Center
    hgap = 10
    vgap = 10
    padding = Insets(10)
  }

  var memberListViewContents = new ObservableBuffer[String]()
  var n = 0
  for (x: String <- databaseHandler.getAllArcherNames()){
    n += 1
    memberListViewContents.append(x)
  }
  memberListView.items = memberListViewContents

  var roundListViewContents = new ObservableBuffer[String]()
  for (x: ImperialRound <- Rounds.getImperial()){
    roundListViewContents.append(x.name)
  }
  roundListView.items = roundListViewContents

  //memberListPane.content = memberListGrid
  memberListPane.content = memberListView
  roundListPane.content = roundListView

  memberListPane.fitToHeight = true
  roundListPane.fitToHeight = true
  memberListPane.maxWidth = 300
  roundListPane.maxWidth = 300
  roundListPane.prefHeight = Double.MaxValue
  memberListPane.prefHeight = Double.MaxValue

  buttonsGrid.add( new Button("Add Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      primaryStage.scene = new AddMember getScene
    }
  }, 0, 0)
  buttonsGrid.add( new Button("Search Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      System.out.println("Search Member")
    }
  }, 0, 1)
  buttonsGrid.add(new Button("Edit Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      System.out.println("Edit Member")
    }
  }, 0, 2)
  buttonsGrid.add( new Button("Delete Member"){
    onAction = (event: javafx.event.ActionEvent) => {
      System.out.println("Delete Member")
    }
  },0, 3)
  buttonsGrid.add(new Button("Add Bulk rounds -> generate PDF (Club shoot)"){
    onAction = (event: javafx.event.ActionEvent) => {
      System.out.println("Add Bulk rounds -> generate PDF (Club shoot)")
    }
  }, 0, 4)

  gridPane.add(new Text("Members"){id="title"}, 0, 1, 1, 1)
  gridPane.add(memberListPane, 0, 2, 1, 5)
  gridPane.add(new Text("Rounds"){id = "title"}, 2, 1, 1, 1)
  gridPane.add(roundListPane, 2, 2, 1, 5)
  gridPane.add(buttonsGrid, 3, 1, 1, 5)
}
