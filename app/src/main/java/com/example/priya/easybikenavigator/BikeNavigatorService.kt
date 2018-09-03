package com.example.priya.easybikenavigator

import android.content.Intent
import android.net.Uri
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import java.util.*

class MyBikeNavigator : TileService(){

    override fun onTileAdded() {
        super.onTileAdded()

        if(qsTile != null ) {
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
        }
    }

    override fun onClick() {
        super.onClick()

        if(qsTile.state == Tile.STATE_INACTIVE){
            qsTile.state = Tile.STATE_ACTIVE
            showNavigation()
        } else {
            qsTile.state = Tile.STATE_INACTIVE
            stopNavigation()
        }

        qsTile.updateTile()
    }

    fun showNavigation(){
//        startTimer()
        var destination = ""
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        when(timeOfDay in 0..11){
            true -> destination = "Work"
            false -> destination = "Home"
        }
        val googleMapsUri = Uri.parse("google.navigation:q=$destination&mode=b")
        val mapIntent = Intent(Intent.ACTION_VIEW, googleMapsUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivityAndCollapse(mapIntent)
    }

    fun stopNavigation(){
    }
}