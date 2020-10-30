package com.example.uidesign.more

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.chaos.view.PinView
import com.example.uidesign.R

class MusicFragment : androidx.fragment.app.Fragment() {
    private val TAG = "MusicFragment"
    private lateinit var pinView1: PinView
    private lateinit var pinView2: PinView
    private lateinit var pinButton: Button
    private var text: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pinView1 = view.findViewById(R.id.pinView1)
        pinView2 = view.findViewById(R.id.pinView2)
        pinButton = view.findViewById(R.id.getPin)

        pinView2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                text = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        pinButton.setOnClickListener {
            Toast.makeText(
                context, "Your Pin is : $text",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}