package com.example.uidesign.history

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.uidesign.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class HistoryFragment : Fragment() {
    private lateinit var name: EditText
    private lateinit var enter: Button
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.nameEditText)
        enter = view.findViewById(R.id.enterButton)
        navController = Navigation.findNavController(view)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                10)

        enter.setOnClickListener {
            val bundle = Bundle()
            Toast.makeText(
                context,
                "Go to WebSocket",
                Toast.LENGTH_SHORT
            ).show()
            bundle.putString("name", name.text.toString())
            navController.navigate(R.id.action_historyFragment2_to_chatFragment, bundle)
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.action_historyFragment2_to_chatFragment -> {
                    bottomMenu.visibility = View.GONE
                }
            }
        }
    }
}