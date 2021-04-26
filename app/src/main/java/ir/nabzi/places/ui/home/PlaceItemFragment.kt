package ir.nabzi.places.ui.home

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nabzi.places.model.Place

typealias CALLBACK =  (String)->Unit

class PlaceItemFragment : Fragment() {
    lateinit var clickListener :  CALLBACK
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val placeview = PlaceView(layoutInflater, container , clickListener)
        placeview.bind(Place.fromBundle(requireArguments()))
        return placeview.view
    }

    companion object {

        /** Creates a Fragment for a given Place  */
        fun create(place: Place , clickListener: CALLBACK): PlaceItemFragment {
            val fragment = PlaceItemFragment()
            fragment.arguments = place.toBundle()
            fragment.clickListener = clickListener
            return fragment
        }
    }
}