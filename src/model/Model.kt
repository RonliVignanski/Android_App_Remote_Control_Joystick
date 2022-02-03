package com.myapp.remotecontroljoystick.model

import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.properties.Delegates

class Model {

    private var socket : Socket? = null
    private var out : PrintWriter? = null
    private var executor : ExecutorService? = null
    var connectionRefreshListListeners = ArrayList<() -> Unit>()
    var isConnected: String by Delegates.observable("false") { property, oldValue, newValue ->
        connectionRefreshListListeners.forEach {
            it()
        }
    }

    init{
        this.executor = Executors.newSingleThreadExecutor()
    }

    fun connect(ip : String, port : Int) {
        this.executor?.execute {
            try {
                this.socket = Socket()
                this.socket?.connect(InetSocketAddress(ip, port), 10)
                this.out = PrintWriter(this.socket!!.getOutputStream(), true)
                this.isConnected = "true"
            } catch (e: Exception) {
                this.isConnected = "error"
            }
        }
    }

    fun disConnect() {
        this.executor?.execute {
            try{
                this.socket?.close()
                out?.close()
                this.isConnected = "false"
            }catch (e: Exception){}
        }
    }

    fun setAileron(aileron: Float) {
        this.executor?.execute {
            try{
                this.out?.print("set /controls/flight/aileron $aileron\r\n")
                this.out?.flush()
            }catch (ERROR: Exception){}
        }
    }

    fun setElevator(elevator: Float) {
        this.executor?.execute {
            try{
                this.out?.print("set /controls/flight/elevator $elevator\r\n")
                this.out?.flush()
            }catch (ERROR: Exception){}
        }
    }

    fun setRudder(rudder: Float) {
        this.executor?.execute {
            try{
                this.out?.print("set /controls/flight/rudder $rudder\r\n")
                this.out?.flush()
            }catch (ERROR: Exception){}
        }
    }

    fun setThrottle(throttle: Float) {
        this.executor?.execute{
            try{
                this.out?.print("set /controls/engines/current-engine/throttle $throttle\r\n")
                this.out?.flush()
            }catch (ERROR: Exception){}
        }
    }
}
