package ir.nabzi.places.model

import android.os.Bundle

class Place
//private constructor
    (val id : String , val title: String, val subTitle: String, val imageUrl:String) {

    fun toBundle(): Bundle {
        val args = Bundle(1)
        args.putStringArray(ARGS_BUNDLE, arrayOf(id , title, subTitle , imageUrl))
        return args
    }

    companion object {
        internal val ARGS_BUNDLE = Place::class.java.name + ":Bundle"


        fun List<Place>.find(id: String): Place? {
            return find { it.id == id }
        }

        fun fromBundle(bundle: Bundle): Place {
            val spec = bundle.getStringArray(ARGS_BUNDLE)
            return Place(spec!![0], spec[1], spec[2], spec[3])
        }
    }
}