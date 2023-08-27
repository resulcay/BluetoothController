package com.example.bluetoothcontroller

import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.annotation.SuppressLint


@Suppress("DEPRECATION")
class IncomingCallReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val telephonyManager = context?.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager

            when (telephonyManager?.callState) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    Toast.makeText(context, "Ringing", Toast.LENGTH_LONG).show()
                    promptToDisableBluetooth(context)
                }
                TelephonyManager.CALL_STATE_IDLE -> Toast.makeText(context, "Idle", Toast.LENGTH_LONG).show()
                TelephonyManager.CALL_STATE_OFFHOOK -> Toast.makeText(context, "Offhook", Toast.LENGTH_LONG).show()
            }
        }
    }
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)
    private fun promptToDisableBluetooth(context: Context?) {

        try {
            val bluetoothManager = context?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            val bluetoothAdapter = bluetoothManager.adapter

            if (bluetoothAdapter.isEnabled) {
                bluetoothAdapter.disable().let {
                    if (it)
                    {
                        Toast.makeText(context, "Bluetooth is OFF", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Disabling failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }


    }
}