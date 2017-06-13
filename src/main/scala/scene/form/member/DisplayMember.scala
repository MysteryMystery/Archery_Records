package scene.form.member

import util.{GUIUtil, ScriptingUtil}
import util.personspecific.Member
import ArcheryRecords.primaryStage
import scene.form.round.{AddRound, ViewStats}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label, ListView}
import scalafx.scene.text.Text
import scalafx.Includes._

/**
  * Project: Archery_Records
  * Package: scene.form.member
  * Created by MysteryMystery under the MIT Liscense.
  */

class DisplayMember(members: List[Member]) extends GUIUtil with ScriptingUtil{
  override protected var sceneTitle: Text = new Text("Member")

  if (members.length > 1){
    var lv: ListView[String] = new ListView[String]()
    var lvContent: ObservableBuffer[String] = ObservableBuffer()
    for (m: Member <- members){
      lvContent.append(s"${m.id}. ${m.forename} ${m.surname}:\n Indoor Class: ${m.indoorClass}\n Outdoor Class: ${m.outdoorClass}\n Postcode: ${m.postcode}")
    }
    lv.items = lvContent
    gridPane.add(lv, 0, 1, 5, 5)
    //TODO add button and listener that allows to go into next part of loop i.e select entity then call primarystage.scene = new DisplayMember(the one member) getScene
  }
  else {

    val member: Member = members.head

    val deleteButton: Button = new Button("Delete"){
      alignment = Pos.CenterRight
      onAction = (event: ActionEvent) => {
        databaseHandler.deleteArcher(member)
        back.fire()
      }
    }
    val viewRounds: Button = new Button("View Stats"){
      onAction = (event: ActionEvent) => {
        primaryStage.scene = new ViewStats(member) getScene
      }
    }
    val addRoundShotButton: Button = new Button("Add Round"){
      onAction = (event: ActionEvent) =>{
        primaryStage.scene = new AddRound(member) getScene

      }
    }

    forenameEntry.text = member.forename
    surnameEntry.text = member.surname
    houseNumberEntry.text = member.houseNumber
    postCodeEntry.text = member.postcode
    indoorClassEntry.text = member.indoorClass
    outdoorClassEntry.text = member.outdoorClass

    submit.onAction = (event: ActionEvent) => {
      val sameVars: mutable.LinkedHashMap[String, String] = mutable.LinkedHashMap()
      val differentVars: mutable.LinkedHashMap[String, String] = mutable.LinkedHashMap()

      if (forenameEntry.text.value == member.forename){
        sameVars += ("forename" -> member.forename)
      }else{
        differentVars += ("forename" -> forenameEntry.text.value)
      }
      if(surnameEntry.text.value == member.surname){
        sameVars += ("surname" -> member.surname)
      }else{
        differentVars += ("surname" -> surnameEntry.text.value)
      }
      if(houseNumberEntry.text.value == member.houseNumber){
        sameVars += ("housenumber" -> houseNumberEntry.text.value)
      }else{
        differentVars += ("housenumber" -> houseNumberEntry.text.value)
      }
      if (postCodeEntry.text.value == member.postcode){
        sameVars += ("postcode" -> postCodeEntry.text.value)
      }else{
        differentVars += ("postcode" -> postCodeEntry.text.value)
      }
      if (indoorClassEntry.text.value == member.indoorClass){
        sameVars += ("indoorclassification" -> indoorClassEntry.text.value)
      }else{
        differentVars+= ("indoorclassification" -> indoorClassEntry.text.value)
      }
      if (outdoorClassEntry.text.value == member.outdoorClass){
        sameVars += ("outdoorclassification" -> outdoorClassEntry.text.value)
      }else{
        differentVars += ("outdoorclassification" -> outdoorClassEntry.text.value)
      }
      databaseHandler.editArcher(sameVars, differentVars)
      warningText.text = "This archer has been eddited successfully."
    }

    addInCol(0, 1, 1, 1,
      idLabel,
      forenameLabel,
      surnameLabel,
      houseNumberLabel,
      postcodeLabel,
      indoorClassLabel,
      outdoorClassLabel,
      back
    )
    addInCol(1, 1, 1, 1,
      new Label(member.id),
      forenameEntry,
      surnameEntry,
      houseNumberEntry,
      postCodeEntry,
      indoorClassEntry,
      outdoorClassEntry,
      submit
    )
    addInCol(2, 1, 1, 1,
      deleteButton,
      viewRounds,
      addRoundShotButton
    )
    gridPane.add(warningText, 0, 10, 2, 1)
  }

}
