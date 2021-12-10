package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import ru.eightythreesoftware.shop_app.android.demo.R
import java.util.*

class MapNavigationFragment : Fragment() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            MapKitFactory.setApiKey("03faf9ef-20ca-4e7b-8af0-30e029dba892")
            MapKitFactory.initialize(this.context);
            super.onCreate(savedInstanceState)
        }catch (throwable: Throwable){
            Toast
                .makeText(
                    this.context,
                    "Произошла ошибка при вызове карты",
                    Toast.LENGTH_LONG)
                .show()
            Log.d("MAIN_DEBUG",throwable.stackTraceToString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.map_navigation_fragment, container, false)
        val searchView: SearchView = view.findViewById(R.id.map_navigation_fragment_search_view)
        val currentLocationButton: ImageView = view.findViewById(R.id.map_navigation_fragment_get_current_button)
        mapView = view.findViewById(R.id.map_view_map)
        mapView.map.move(
            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
        return view
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    private fun getUserCurrentPosition(){
       TODO()
    }

    private fun requestPermission(){
        TODO()
    }

    private fun getFinalDestinationPoints(){
        TODO()
    }

    private fun searchUserTypingDestination(){
        TODO()
    }
}