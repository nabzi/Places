package ir.nabzi.places.ir.nabzi.places.ui

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showError(message: String?) {
    Toast.makeText(requireContext(), message ?: "error", Toast.LENGTH_SHORT).show()
}