package com.example.bluetoothcontroller

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED)
        {
           askForPhoneState()
        }


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED)
        {
            askForBluetooth()
        }
    }

    private  fun  askForPhoneState()
    {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_PHONE_STATE), 100
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private  fun   askForBluetooth()
    {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT), 100
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.BLUETOOTH_SCAN), 100
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.BLUETOOTH_PRIVILEGED), 100
        )
    }
}
