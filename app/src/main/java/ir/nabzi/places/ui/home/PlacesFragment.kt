package ir.nabzi.places.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.TransitionOptions
import ir.nabzi.places.R
import ir.nabzi.places.model.Place
import kotlinx.android.synthetic.main.fragment_places.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel


class PlacesFragment : Fragment() {
    private val vmodel: PlaceViewModel by sharedViewModel()
    val ACCESS_TOKEN =
        "sk.eyJ1IjoibmFiemkiLCJhIjoiY2tueGFuMWJyMTRqMTJ2cW42NjUya3dzaSJ9.0uj9WisAz4Xd18I8s5rW9g"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Mapbox.getInstance(requireContext(), ACCESS_TOKEN);
        subscribeUi()
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

//    val places = arrayListOf(
//        Place("id1", "cafe", "", "" , 10),
//        Place("id2", "park", "", "",9),
//        Place("id3", "school", "", "",8)
//    )
    private fun subscribeUi() {
        this.lifecycleScope.launch {
            vmodel.placeList.collect { resource ->
                resource?.data?.let {
                    if(it.isNotEmpty())
                         showPlaces(it)
                }
            }
        }
    }

    private fun showPlaces(places: List<Place>) {
        initViewPager(places)
        initMap(places)
    }

    private fun initMap(places: List<Place>) {
        mapView.getMapAsync(OnMapReadyCallback { mapboxMap ->
            mapboxMap.setStyle(Style.MAPBOX_STREETS,
                object : Style.OnStyleLoaded {
                    override fun onStyleLoaded(style: Style) {
                        style.setTransition(TransitionOptions(0, 0, false));
                        val position = CameraPosition.Builder()
                            .target(LatLng(places[1].location_lat, places[1].location_lng))
                            .zoom(15.0)
                            .build()
                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1000);
                        for (place in places)
                            mapboxMap.addMarker(
                                MarkerOptions()
                                    .position(LatLng(place.location_lat, place.location_lng))
                                    .title(place.id)
                            )
                        mapboxMap.setOnMarkerClickListener { it ->
                            selectPlace(it.title)
                            //Toast.makeText(requireContext() , it.title , Toast.LENGTH_SHORT).show()
                            true
                        }
                    }
                }
            )
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState);
    }

    private fun initViewPager(places: List<Place>) {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return PlaceItemFragment.create(
                    places[position]
                ) { id -> Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        PlacesFragmentDirections.actionPlacesFragmentToPlaceDetailsFragment(places[position].id)
                    )
                }
            }

            override fun getItemCount(): Int {
                return places.size
            }
        }
    }

    private fun selectPlace(title: String?) {
        title?.toIntOrNull()?.let {
            viewPager.setCurrentItem(it)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}
