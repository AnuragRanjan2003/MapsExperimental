package com.example.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var latED :EditText
    private lateinit var lonED :EditText
    private lateinit var go:Button
    private lateinit var map : GoogleMap // instance of gMap
    private lateinit var myMarker : Marker // my location marker
    private lateinit var newMarker : Marker // test marker
    private var loc = LatLng(0.00,0.00) // test initial location
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        latED = findViewById(R.id.lat)
        lonED = findViewById(R.id.lon)
        go = findViewById(R.id.go)

        go.setOnClickListener {
            loc = LatLng(latED.text.toString().toDouble(),lonED.text.toString().toDouble()) // test loc modified
            newMarker.position = loc // marker's loc updated (not MarkerOption's)
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,17.0f))

        }
        // generate map fragment and set map
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    // on map first time created
    override fun onMapReady(p0: GoogleMap) {
        this.map = p0 // set global var of map( can only be caught here)
        p0.mapType=GoogleMap.MAP_TYPE_NORMAL
        p0.addMarker(MarkerOptions().position(LatLng(0.00,0.0)).title("my"))?.let{ this.myMarker = it} // add my marker
        p0.addMarker(MarkerOptions().position(LatLng(0.00,0.0)).title("new"))?.let{this.newMarker = it} // add test marker


        p0.moveCamera(CameraUpdateFactory.newLatLng(loc))
    }


}