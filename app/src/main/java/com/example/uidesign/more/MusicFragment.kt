package com.example.uidesign.more

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.chaos.view.PinView
import com.example.uidesign.R

class MusicFragment : androidx.fragment.app.Fragment() {
    private val TAG = "MusicFragment"
    private lateinit var pinView1: PinView
    private lateinit var pinView2: PinView
    private lateinit var pinButton: Button
    private lateinit var countText: TextView

    private lateinit var increaseButton: ImageButton
    private lateinit var decreaseButton: ImageButton
    var count: Int = 0

    private var pinViewText1: String? = null
    private var pinViewText2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pinView1 = view.findViewById(R.id.pinView1)
        pinView2 = view.findViewById(R.id.pinView2)
        pinButton = view.findViewById(R.id.getPin)
        countText = view.findViewById(R.id.textCount)
        increaseButton = view.findViewById(R.id.increaseCount)
        decreaseButton = view.findViewById(R.id.decreaseCount)
        Log.d(TAG, "onViewCreated: $count")
        pinView1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pinViewText1 = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        pinView2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pinViewText2 = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        pinButton.setOnClickListener {
            Toast.makeText(
                context, "Your Pin is : $pinViewText1 + $pinViewText2",
                Toast.LENGTH_SHORT
            ).show()
        }


        increaseButton.setOnClickListener {
            Log.d(TAG, "onViewCreated: $count")
            if (count <= 3) {
                count += 1
                countText.text = " $count نفر "
            } else if (count == 4) {
                count += 1
                countText.text = " $count نفر (1 نفر اضافه) "
            }
        }

        decreaseButton.setOnClickListener {
            if (count == 0) {
                countText.text = ""
                Toast.makeText(
                    context, "Count can't be negative",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (count == 1) {
                count -= 1
                countText.text = ""
            } else {
                count -= 1
                countText.text = " $count نفر "
            }
        }
    }

}