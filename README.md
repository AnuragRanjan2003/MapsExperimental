# MapsExperimental
experimenting with google maps

An experimental android app where in all required features of Google maps were implemented.
Extend the activity so as to implement `OnMapReadyCallback` 

## Set Up
 #### 1. Add dependency.
 #### 2. Add Api Key as String in string res `string.xml` file.
 #### 3. Add permisions of location and internet.
 #### 4. Add to `activity.xml` file
 ```xml
     <fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/linearLayout"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:layout="@android:layout/activity_list_item" />
```
## Implementation
### 1. Set Map View
  in mainActivity
```kotlin
  val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
```

### 2. To implement and get instance of map
```kotlin
   override fun onMapReady(p0: GoogleMap) {
        this.map = p0 // set global var of map( can only be caught here)
        p0.mapType=GoogleMap.MAP_TYPE_NORMAL
        p0.addMarker(MarkerOptions().position(LatLng(0.00,0.0)).title("my"))?.let{ this.myMarker = it} // add my marker
        p0.addMarker(MarkerOptions().position(LatLng(0.00,0.0)).title("new"))?.let{this.newMarker = it} // add test marker


        p0.moveCamera(CameraUpdateFactory.newLatLng(loc))
    }
```
### 3. Update location
 To update the location of a marker 
  1. Create a global instance of the marker ``  private lateinit var newMarker : Marker ``
  2. Create the marker on mapCreated 
  ```kotlin
      gMap.addMarker(MarkerOptions().position(LatLng(0.00,0.0)).title("new"))?.let{
        this.newMarker = it
        }
   ```
  3. On location update use ``  newMarker.position = loc ``
  4. use `` map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,17.0f)) `` to focus on new location

