package edu.fullerton.fz.cs411.inputtoastloggingspring24

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast

const val LOG_TAG = "MyLogLine"

class MainActivity : AppCompatActivity() {

    private lateinit var firstButton: Button
    private lateinit var secondButton: Button
    private lateinit var thirdButton: Button
    private lateinit var mainText: EditText
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mainSwitch: Switch
    private lateinit var mainSeekBar: SeekBar

    private val myButtonClickListener = View.OnClickListener { view: View ->
        val btn = view as Button
        Log.i(LOG_TAG, "${btn.text} was tapped")
    }
    private val myButtonLongPressListener = View.OnLongClickListener {
        val btn = it as Button
        Log.w(LOG_TAG, "${btn.text} was long pressed")
        true // Event was consumed, so it won't grab a click after
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectViewPointers()
        setupCallbacks()
        demonstrateDebugging()
    }

    private fun demonstrateDebugging() {
        // TODO("Not yet implemented")
        // throw Exception("There was an unknown error")
        // assert(mainSwitch.isChecked)
        for (i in 1 .. 10 ) {
            var b = i * 5
            for (j in 10 .. 20 step 3) {
                val c = b + j * 10
            }
        }
    }

    private fun setupSeekbarCallback() {
        mainSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.v(LOG_TAG, "Seekbar value has changed to ${p0?.progress}")
            }
            override fun onStartTrackingTouch(p0: SeekBar?) { }
            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun setupSwitchCallback() {
        mainSwitch.setOnClickListener {
            val sw = it as Switch
            Log.v(LOG_TAG, "Switch was changed to ${sw.isChecked}")
        }
    }

    private fun setupCallbacks() {
        setupButtonCallbacks()
        setupTextCallback()
        setupSwitchCallback()
        setupSeekbarCallback()
    }

    private fun setupTextCallback() {
        mainText.setOnKeyListener { _, i, keyEvent ->
            // _ is the unused parameter of type View
            Log.v(LOG_TAG,"Text key $i was pressed. The event is $keyEvent.")
            false // consumed
        }
    }

    private fun setupButtonCallbacks() {
        setupFirstButtonCallback()
        setupSecondAndThirdButtonCallbacks()
    }

    private fun setupSecondAndThirdButtonCallbacks() {
        secondButton.setOnClickListener(myButtonClickListener)
        thirdButton.setOnClickListener(myButtonClickListener)
        secondButton.setOnLongClickListener(myButtonLongPressListener)
        thirdButton.setOnLongClickListener(myButtonLongPressListener)
    }

    private fun setupFirstButtonCallback() {
        firstButton.setOnClickListener {
            val btn = it as Button
            // println("${btn.text} was clicked")
            Log.v(LOG_TAG, "${btn.text} was clicked")
            if (mainSwitch.isChecked) {
                Toast.makeText(this, R.string.you_are_happy, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.you_are_not_happy, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connectViewPointers() {
        firstButton = findViewById(R.id.button1)
        secondButton =  findViewById(R.id.button2)
        thirdButton =  findViewById(R.id.button3)
        mainText =  findViewById(R.id.editText)
        mainSwitch =  findViewById(R.id.mySwitch)
        mainSeekBar =  findViewById(R.id.seekBar)
    }
}