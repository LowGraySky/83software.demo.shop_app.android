package ru.eightythreesoftware.shop_app.android.demo

import android.app.Activity
import android.app.Application
import com.yandex.mapkit.MapKitFactory
import ru.eightythreesoftware.shop_app.android.demo.search.SearchActivity

class CurrentApplication: Application() {

     lateinit var mapKitFactory: MapKitFactory
     lateinit var searchActivity: Activity

     override fun onCreate() {
          super.onCreate()
          initializeSearchActivity()
          initializeMap()
     }

     private fun initializeMap(){
          mapKitFactory = MapKitFactory()
          MapKitFactory.setApiKey("03faf9ef-20ca-4e7b-8af0-30e029dba892")
     }

     private fun initializeSearchActivity(){
          searchActivity = SearchActivity()
     }
}

