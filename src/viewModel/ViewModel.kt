package com.myapp.remotecontroljoystick.viewModel 

import com.example.myfirstapplication.model.Model
import kotlin.properties.Delegates

class ViewModel {
    var model = Model()

    var connectionRefreshListListeners = ArrayList<() -> Unit>()
    var isConnected: String by Delegates.observable("false") { property, oldValue, newValue ->
        connectionRefreshListListeners.forEach {
            it()
        }
    }

    init{
        this.model.connectionRefreshListListeners.add {
            isConnected = this.model.isConnected
        }
    }

    fun connect(ip: String, port: Int) {
        this.model.connect(ip, port)
    }

    fun disConnect() {
        this.model.disConnect()
    }

    fun setAileron(aileron: Float) {
        this.model.setAileron(aileron)
    }

    fun setElevator(elevator: Float) {
        this.model.setElevator(elevator)
    }

    fun setRudder(rudder: Float) {
        this.model.setRudder(rudder)
    }

    fun setThrottle(throttle: Float) {
        this.model.setThrottle(throttle)
    }
}