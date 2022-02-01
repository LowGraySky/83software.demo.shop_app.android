package ru.eightythreesoftware.shop_app.android.demo

import android.app.Activity
import android.app.Application
import com.yandex.mapkit.MapKitFactory

class CurrentApplication: Application() {

     lateinit var mapKitFactory: MapKitFactory

     override fun onCreate() {
          super.onCreate()
          initializeMap()
     }

     private fun initializeMap(){
          mapKitFactory = MapKitFactory()
          MapKitFactory.setApiKey("03faf9ef-20ca-4e7b-8af0-30e029dba892")
     }
}

