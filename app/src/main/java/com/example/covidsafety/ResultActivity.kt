package com.example.covidsafety

import android.content.Intent

import android.graphics.Bitmap
import android.graphics.Color

import android.os.Bundle

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import eu.example.covidSafety.R
import kotlinx.android.synthetic.main.activity_result.*




//private const val  tag="page9"

class ResultActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        generate_button.setOnClickListener {
            val name = name_qr.text.toString()
            val address = address_qr.text.toString()
            val phone = phone_number_qr.text.toString()
            val gateId = gateid_qr.text.toString()
            val userScore = intent.getIntExtra(Constants.TOTAL_SCORE, 0)

            val score0= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 0%"
            val score1= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 8.33%"
            val score2= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 16.66%"
            val score3= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 25%"
            val score4= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 33.33%"
            val score5= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 41.65%"
            val score6= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 50%"
            val score7= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 58.33%"
            val score8= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 66.66%"
            val score9= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 75%"
            val score10= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 83.33%"
            val score11= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 91.66%"
            val score12= "NAME = $name\n\nADDRESS = $address\n\nPHONE NO. = $phone\n\nGATE.ID = $gateId\n\nPERCENTAGE CHANCES = 100%"

            if (name.isBlank()) {
                Toast.makeText(this,"Enter name to generate QR",Toast.LENGTH_SHORT).show()
            }
            else if (address.isBlank()) {
                Toast.makeText(this,"Enter address to generate QR",Toast.LENGTH_SHORT).show()
            }
           else if (phone.isBlank()) {
                Toast.makeText(this,"Enter phone number to generate QR",Toast.LENGTH_SHORT).show()
            }
            else if (gateId.isBlank()) {
                Toast.makeText(this,"Enter Gate ID to generate QR",Toast.LENGTH_SHORT).show()
            }
            else {
                if(userScore==0) {
                    val bitmap = generateQRCode(score0)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==1) {
                    val bitmap = generateQRCode(score1)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==2) {
                    val bitmap = generateQRCode(score2)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==3) {
                    val bitmap = generateQRCode(score3)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==4) {
                    val bitmap = generateQRCode(score4)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==5) {
                    val bitmap = generateQRCode(score5)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==6) {
                    val bitmap = generateQRCode(score6)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==7) {
                    val bitmap = generateQRCode(score7)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==8) {
                    val bitmap = generateQRCode(score8)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==9) {
                    val bitmap = generateQRCode(score9)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==10) {
                    val bitmap = generateQRCode(score10)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==11) {
                    val bitmap = generateQRCode(score11)
                    QRIMAGE.setImageBitmap(bitmap)
                }
                if(userScore==12) {
                    val bitmap = generateQRCode(score12)
                    QRIMAGE.setImageBitmap(bitmap)
                }
            }
        }

        back_main.setOnClickListener {
            startActivity(
                Intent(this, BrowserActivities::class.java)
            )
            finish()
        }

    }

    private fun generateQRCode(name:String):Bitmap{
        val width=300
        val height=300
        val bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        val codeWriter=MultiFormatWriter()
        try{
            val bitMatrix=codeWriter.encode(name,BarcodeFormat.QR_CODE,width,height)
            for(x in 0 until width){
                for (y in 0 until height){
                    bitmap.setPixel(x,y,if(bitMatrix[x,y])Color.BLACK else Color.WHITE)
                }
            }
        }catch (e:WriterException){
            Log.d(e.toString(),"GenerateQRCode:${e.message}")
        };return bitmap
    }
}