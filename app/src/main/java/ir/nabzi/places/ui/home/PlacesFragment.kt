package ir.nabzi.places.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import ir.nabzi.places.R
import ir.nabzi.places.model.Place
import kotlinx.android.synthetic.main.fragment_places.*


class PlacesFragment : Fragment() {
    val ACCESS_TOKEN = "sk.eyJ1IjoibmFiemkiLCJhIjoiY2tueGFuMWJyMTRqMTJ2cW42NjUya3dzaSJ9.0uj9WisAz4Xd18I8s5rW9g"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireContext(), ACCESS_TOKEN);
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    val places = arrayListOf(
        Place("id1", "cafe", "", ""),
        Place("id2", "park", "", ""),
        Place("id3", "school", "", "")
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return PlaceItemFragment.create(places[position])
            }

            override fun getItemCount(): Int {
                return 3
            }
        }
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(OnMapReadyCallback {
            it.setStyle(Style.MAPBOX_STREETS,
                object : Style.OnStyleLoaded{
                    override fun onStyleLoaded(style: Style) {
                        //TODO("Not yet implemented")
                    }
                }
            )})
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
