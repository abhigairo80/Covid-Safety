@file:Suppress("ControlFlowWithEmptyBody")

package eu.example.covidSafety.covidsafety

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*

import eu.example.covidSafety.R
import kotlinx.android.synthetic.main.activity_scanner.*


private const val CAMERA_REQUEST_CODE =101

class Scanner : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        setupPermission()
        codeScanner()
    }
    private fun codeScanner(){
        codeScanner=CodeScanner(this,scanner_view)
        codeScanner.apply {
            camera=CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode= ScanMode.CONTINUOUS
            isAutoFocusEnabled=true
            isFlashEnabled=false
            decodeCallback = DecodeCallback{
                runOnUiThread{
                    tv_view.text =it.text
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main","Camera initialization error : ${it.message}")
                }
            }
        }
        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

private fun setupPermission(){
    val permission=ContextCompat.checkSelfPermission(this,
    android.Manifest.permission.CAMERA)
    if (permission!= PackageManager.PERMISSION_GRANTED){
        makerequest()
    }
}
private fun makerequest(){
    ActivityCompat.requestPermissions(this, arrayOf( android.Manifest.permission.CAMERA),
        CAMERA_REQUEST_CODE
    )
}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isEmpty()||grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"You need the Camera Permission",Toast.LENGTH_SHORT).show()
                }
                else{

                }
            }
        }
    }
}
