package ir.nabzi.places.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nabzi.places.model.Place

typealias CALLBACK = (String)->Unit

class PlaceItemFragment : Fragment() {
    var clickListener :  CALLBACK? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val placeview = PlaceView(layoutInflater, container, clickListener)
        requireArguments().getParcelable<Place>(KEY_PLACE)?.let{
            placeview.bind(it)
        }
        return placeview.binding.root
    }

    companion object {
        const val KEY_PLACE = "key_place"
        fun create(place: Place, listener: CALLBACK): PlaceItemFragment {

            val frag =  PlaceItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PLACE, place)
                }
            }
            frag.clickListener = listener
            return frag
        }
    }
}