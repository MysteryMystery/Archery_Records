package scene.form.round

import java.sql.ResultSet

import util.GUIUtil
import util.personspecific.Member
import util.archeryspecific.classification.ClassificationRequirements.{calculateClassfication, calculateHandicap}
import util.ImplicitHelpers.PaneHelper

import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{Background, GridPane, Pane}
import scalafx.scene.text.Text

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */
class ViewStats(member: Member) extends GUIUtil{
  override protected var sceneTitle: Text = new Text(s"Stats for ${member.forename + " " + member.surname}")


  var statsPane: GridPane = new GridPane(){
    padding = Insets(20)
  }
  var line = 1
  var lineStyle = "-fx-background-color: #CC99FF"
  val stats: ResultSet = databaseHandler.getStats(member.id.toInt)
  val p1 = new Pane(){
      children = new Label("Round Name |")
      style = lineStyle
    }
  val p2 = new Pane(){
      children = new Label(" Bow Type |")
      style = lineStyle
    }
  val p3 =  new Pane(){
      children = new Label(" Score |")
      style = lineStyle
    }
  val p4 =  new Pane(){
      children = new Label(" Handicap |")
      style = lineStyle
    }
  val p5 = new Pane(){
    children =  new Label(" Classification ")
    style = lineStyle
   }
  statsPane.addRow(0, p1, p2, p3, p4, p5)
  while (stats.next()){
    lineStyle = if (line % 2 == 0) "-fx-background-color: #61C2FF;" else "-fx-background-color: #e1e2de;"
    var rn = stats.getString(1)
    var sr = stats.getInt(3)
    var l1 = new Pane(){
      children = new Label(stats.getString(1))
      style = lineStyle
    }
    var l2 = new Pane(){
      children = new Label(stats.getString(2))
      style = lineStyle
    }
    var l3 = new Pane(){
      children = new Label(stats.getString(3))
      style = lineStyle
    }
    var l4 = new Pane(){
      children = new Label(calculateHandicap(rn, sr).toString)
      style = lineStyle
    }
    var l5 = new Pane(){
      children = new Label(calculateClassfication(rn, sr).toString)
      style = lineStyle
    }
    statsPane.addRow(line, l1, l2, l3, l4, l5)
    line += 1
  }
  gridPane.add(new ScrollPane(){content = statsPane}, 0, 1)
}
