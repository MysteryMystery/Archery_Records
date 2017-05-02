package scene.form.mainmenu

import data.{ConfigLoader, DatabaseHandler}
import util.{GUIUtil, ScriptingUtil}

import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, ScrollPane}
import scalafx.scene.text.Text
import ArcheryRecords._
import scene.form.member.AddMember
import util.personspecific.Member

import scalafx.geometry.{Insets, Pos}
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
  var memberListGrid: GridPane = new GridPane()
  var roundListPane: ScrollPane = new ScrollPane()
  var roundListGrid: GridPane = new GridPane()

  var buttonsGrid: GridPane = new GridPane(){
    alignment = Pos.Center
    hgap = 10
    vgap = 10
    padding = Insets(10)
  }

  memberListGrid.add(new Text("Members"){
    id = "sceneTitle"
  }, 0, 0)
  var z: Int = 0
  for (x: Member <- databaseHandler.getAllMembers()){
    z += 1
    memberListGrid.add(new Text(x.name), 0, z)
  }

  memberListPane.content = memberListGrid
  roundListPane.content = roundListGrid

  memberListPane.fitToHeight = true
  roundListPane.fitToHeight = true
  memberListPane.maxWidth = 300
  roundListPane.maxWidth = 300
  roundListPane.prefHeight = Double.MaxValue
  memberListPane.prefHeight = Double.MaxValue

  roundListGrid.add(new Text("Rounds"){id = "sceneTitle"}, 0, 0)

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

  gridPane.add(memberListPane, 0, 1, 1, 5)
  gridPane.add(roundListPane, 2, 1, 1, 5)
  gridPane.add(buttonsGrid, 3, 1, 1, 5)
/*
  addInCol(
    4, 3, 1, 1,
    new Button("Add Member"){
      onAction = (event: javafx.event.ActionEvent) => {
        primaryStage.scene = new AddMember getScene
      }
    },
    new Button("Search Member"){
      onAction = (event: javafx.event.ActionEvent) => {
        System.out.println("Search Member")
      }
    },
    new Button("Edit Member"){
      onAction = (event: javafx.event.ActionEvent) => {
        System.out.println("Edit Member")
      }
    },
    new Button("Delete Member"){
      onAction = (event: javafx.event.ActionEvent) => {
        System.out.println("Delete Member")
      }
    }
  )
  addInCol(5, 1, 1, 1,
    new Button("Add new score")
  )
  */
}
