package ir.nabzi.places.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.nabzi.places.R
import ir.nabzi.places.model.Place
import kotlinx.android.synthetic.main.fragment_places.*

class PlacesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    val places = arrayListOf(
        Place("id1","cafe" , "" , "") ,
        Place("id2","park" , "" , "") ,
        Place("id3","school" , "" , "")
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
    }

}
