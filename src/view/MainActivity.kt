package com.myapp.remotecontroljoystick.view

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R
import com.example.myfirstapplication.viewModel.ViewModel
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var viewModel = ViewModel()
    var isConnected : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rudder = findViewById<SeekBar>(R.id.rudder)
        val throttle = findViewById<SeekBar>(R.id.throttle)
        setSeekBarChangeEvent(rudder, "rudder")
        setSeekBarChangeEvent(throttle, "throttle")

        val joystick = findViewById<Joystick>(R.id.joystick_layout)
        joystick.onChange = fun(aileron, elevator): Boolean{
            viewModel.setAileron(aileron)
            viewModel.setElevator(elevator)
            return true
        }

        val connectBtn = findViewById<Button>(R.id.connectButton)
        val disconnectBtn = findViewById<Button>(R.id.disconnectButton)
        connectBtn.setOnClickListener{
            connectBtnOnClick()
        }
        disconnectBtn.setOnClickListener{
            disconnectBtnOnClick()
        }

        this.viewModel.connectionRefreshListListeners.add {
            when (this.viewModel.isConnected) {
                "true" -> {
                    isConnected = true
                    showMsg("connected", Color.GREEN)
                }
                "error" -> {
                    isConnected = false
                    showMsg("error! please try again", Color.RED)
                }
                else -> { // "false"
                    isConnected = false
                    showMsg("disconnected", Color.RED)
                }
            }
        }
    }

    private fun setSeekBarChangeEvent(seekBar: SeekBar, seek: String) {

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                if(seek == "rudder"){
                    viewModel.setRudder(progress/1000F)
                }else{ // throttle
                    viewModel.setThrottle(progress/1000F)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    private fun showMsg(msg : String, color: Int){
        val errorMsgTextBox = findViewById<TextView>(R.id.errorMsgTextBox)
        errorMsgTextBox.text = msg
        errorMsgTextBox.setTextColor(color)
    }

    private fun connectBtnOnClick(){

        if(isConnected){
            showMsg("please disconnect first", Color.RED)
            return
        }

        val ip = findViewById<EditText>(R.id.TextForIP)
        val port = findViewById<EditText>(R.id.TextForPort)
        try{
            viewModel.connect(ip.text.toString(), port.text.toString().toInt())
        }catch (ERROR: Exception){
           showMsg("please insert ip and port", Color.RED)
        }
    }

    private fun disconnectBtnOnClick(){
        if(!isConnected){
            return
        }
        viewModel.disConnect()
    }
}