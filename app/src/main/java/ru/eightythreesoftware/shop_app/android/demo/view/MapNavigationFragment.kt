package ru.eightythreesoftware.shop_app.android.demo.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import ru.eightythreesoftware.shop_app.android.demo.R

class MapNavigationFragment : Fragment(), LocationListener {

    private  val MIN_UPDATE_INTERVAL: Long = 1000
    private  val MIN_LOCATION_DISTANCE: Float = 1F
    private lateinit var mapView: MapView
    private lateinit var locationManager: LocationManager
    private val requiredPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){ permissions: Map<String, Boolean> ->
        if (permissions.all { it.value }){
            Toast.makeText(
                this.context,
                "Разрешение выдано",
                Toast.LENGTH_LONG
            ).show()
            Log.d("MAIN_DEBUG", "INFO: location permissions granted: ${permissions.keys.map { it.toString() }}")
        }else{
            Toast.makeText(
                this.context,
                "Разрешение не выдано",
                Toast.LENGTH_LONG
            ).show()
            Log.d("MAIN_DEBUG", "INFO: location permission NOT granted: ${permissions.keys.map { it.toString() }}"  )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            if(this.context?.let { context ->
                    ActivityCompat.checkSelfPermission(context, requiredPermissions[0]) } == PackageManager.PERMISSION_DENIED &&
                this.context?.let { ActivityCompat.checkSelfPermission(it, requiredPermissions[1]) } == PackageManager.PERMISSION_DENIED
            ){
                permissionLauncher
                    .launch(requiredPermissions)
            }
            MapKitFactory.setApiKey("03faf9ef-20ca-4e7b-8af0-30e029dba892")
            MapKitFactory.initialize(this.context)
            locationManager = this.activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
        val view = inflater.inflate(R.layout.map_navigation_fragment, container, false)
        try{
            val searchView: SearchView = view.findViewById(R.id.map_navigation_fragment_search_view)
            val currentLocationButton: ImageView = view.findViewById(R.id.map_navigation_fragment_get_current_button)
            mapView = view.findViewById(R.id.map_view_map)
            mapView.map.move(
                CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0F),
                null
            )
            currentLocationButton.setOnClickListener {
                getUserCurrentPosition(locationManager)
            }
            return view
        }catch (throwable: Throwable){
            Toast
                .makeText(
                    this.context,
                    "Произошла ошибка при вызове карты",
                    Toast.LENGTH_LONG)
                .show()
            Log.d("MAIN_DEBUG",throwable.stackTraceToString())
        }
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

    private fun getEnabledLocationProvider(locationManager: LocationManager): String{
        val provider =  if(locationManager.
            isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) LocationManager.NETWORK_PROVIDER else LocationManager.GPS_PROVIDER
        Log.d("MAIN_DEBUG", "INFO: Providers status:" +
                " GPS: ${locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)}," +
                " NETWORK: ${locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)}")
        return provider
    }

    private fun getUserCurrentPosition(locationManager: LocationManager){
        if(this.context?.let { context->
                ActivityCompat.checkSelfPermission(context, requiredPermissions[0]) } == PackageManager.PERMISSION_GRANTED &&
            this.context?.let { ActivityCompat.checkSelfPermission(it, requiredPermissions[1]) } == PackageManager.PERMISSION_GRANTED
        ){
            val provider = getEnabledLocationProvider(locationManager)
            locationManager.requestLocationUpdates(
                provider,
                MIN_UPDATE_INTERVAL,
                MIN_LOCATION_DISTANCE,
                this,
                Looper.getMainLooper()
            )
        }else{
            permissionLauncher
                .launch(requiredPermissions)
        }
    }


    private fun getFinalDestinationPoints(){
        TODO()
    }

    private fun searchUserTypingDestination(){
        TODO()
    }

    override fun onLocationChanged(location: Location) {
        Log.d("MAIN_DEBUG", "MAP: got location updates: latitude ${location.latitude}, longitude ${location.longitude}")
        mapView.map
            .move(
                CameraPosition(
                    Point(location.latitude, location.longitude), mapView.z, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0F)
            ) { isExecuted: Boolean ->
                if (!isExecuted) {
                    Toast.makeText(
                        this.context,
                        "При определении геолокации произошла ощиюка",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("MAIN_DEBUG", "MAP: FAIL: Camera hasn't moved")
                }
            }
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
    }
}