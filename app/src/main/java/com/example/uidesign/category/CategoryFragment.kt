package com.example.uidesign.category

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uidesign.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class CategoryFragment : Fragment() {
    private lateinit var switchOne: SwitchMaterial
    private lateinit var switchTwo: SwitchMaterial
    private lateinit var switchThree: SwitchMaterial
    private lateinit var switchFour: SwitchMaterial
    private lateinit var submit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchOne = view.findViewById(R.id.firstSwitch)
        switchTwo = view.findViewById(R.id.secondSwitch)
        switchThree = view.findViewById(R.id.thirdSwitch)
        switchFour = view.findViewById(R.id.forthSwitch)
        submit = view.findViewById(R.id.button)

        submit.setOnClickListener {

            val one: String = if (switchOne.isChecked) {
                switchOne.textOn.toString()
            } else {
                switchOne.textOff.toString()
            }

            val two: String = if (switchTwo.isChecked) {
                switchTwo.textOn.toString()
            } else {
                switchTwo.textOff.toString()
            }

            val three: String = if (switchThree.isChecked) {
                switchThree.textOn.toString()
            } else {
                switchThree.textOff.toString()
            }

            val four: String = if (switchFour.isChecked) {
                switchFour.textOn.toString()
            } else {
                switchFour.textOff.toString()
            }
            Toast.makeText(
                context,
                "Switch1 -  " + one + " \n" + "Switch2 - " + two + " \n" + "Switch3 -  " + three + " \n"
                        + "Switch4 - " + four,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}