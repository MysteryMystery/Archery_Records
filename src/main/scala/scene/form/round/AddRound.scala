package scene.form.round

import scene.form.member.DisplayMember
import util.personspecific.Member
import util.{GUIUtil, ScriptingUtil}
import util.archeryspecific._

import scalafx.collections.ObservableBuffer
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, ChoiceBox, TextField, Tooltip}
import scalafx.scene.text.Text

/**
  * Project: Archery_Records
  * Package: scene.form.round
  * Created by MysteryMystery under the MIT Liscense.
  */
class AddRound(member: Member) extends GUIUtil with ScriptingUtil{
  override protected var sceneTitle: Text = new Text(s"Add Round for ${member.forename} ${member.surname}")

  var roundChoice: ChoiceBox[String] = _
  setupRoundChoice

  var bowChoice: ChoiceBox[String] = _
  setupBowChoice

  var scoreField: TextField = _
  setupScoreField

//  addInCol(0, 1, 3, 4,
//    roundChoice
//  )

  addInRow(0, 1, 2, 4,
    roundChoice, bowChoice
  )
  gridPane.add(scoreField, 1, 1, 1, 1)
  addInRow(0, 2, 1, 1, new Button("Add Round"){
    defaultButton = true
    onAction = (event: javafx.event.ActionEvent) => {
      println(roundChoice.getSelectionModel.getSelectedItem)
      println(bowChoice.getSelectionModel.getSelectedItem)
      println(scoreField.text)
      databaseHandler.addRound(member.id.toInt, bowChoice.getSelectionModel.getSelectedItem, roundChoice.getSelectionModel.getSelectedItem, scoreField.text.value.toInt)
      ArcheryRecords.primaryStage.scene = new DisplayMember(List(member)) getScene
    }
  })

  def setupRoundChoice: Unit = {
    roundChoice = new ChoiceBox[String](){
      tooltip = new Tooltip("Select the round shot")
      onAction = (event: javafx.event.ActionEvent) => {
        println(roundChoice.getSelectionModel.getSelectedItem)
      }
      maxWidth = 250
      prefWidth = 250
    }

    val roundChoiceContents: ObservableBuffer[String] = new ObservableBuffer[String]()
    for (round: GenericRound <- Rounds.getImperial().asInstanceOf[List[GenericRound]] ++ Rounds.getMetric().asInstanceOf[List[GenericRound]] ++ Rounds.getIndoor().asInstanceOf[List[GenericRound]]){
      round match {
        case _ : ImperialRound => roundChoiceContents.append(round.asInstanceOf[ImperialRound].name)
        case _: MetricRound => roundChoiceContents.append(round.asInstanceOf[MetricRound].name)
        case _: IndoorRound => roundChoiceContents.append(round.asInstanceOf[IndoorRound].name)
      }
    }
    roundChoice.items = roundChoiceContents
    roundChoice.getSelectionModel.selectFirst()
  }

  def setupBowChoice: Unit = {
    val bowChoiceContents = new ObservableBuffer[String]()
    bowChoiceContents.append(Recurve.displayName, Compound.displayName, Longbow.displayName)
    bowChoice = new ChoiceBox[String](){
      tooltip = new Tooltip("Select the bow type")
      onAction = (event: javafx.event.ActionEvent) => {
        println(bowChoice.getSelectionModel.getSelectedItem)
      }
      items = bowChoiceContents
      maxWidth = 150
      prefWidth = 150
    }
    bowChoice.getSelectionModel.selectFirst()
  }

  def setupScoreField: Unit = {
    scoreField = new TextField(){
      promptText = "Score"
      alignmentInParent = Pos.CenterRight
      prefWidth = 100
      maxWidth = 100
    }
  }
}
