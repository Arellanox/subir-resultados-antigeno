package com.example.refineria.ANTIGENO

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import com.example.refineria.DatePickerFragment
import com.example.refineria.R
import kotlinx.android.synthetic.main.activity_antigeno_resultado_formulario.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ANTIGENO_resultado_formulario : AppCompatActivity() {
    val listMuestras = ArrayList<String>()
    val ListIDMuestras = ArrayList<String>()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_resultado_formulario)
        setupListener()
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd")
        ResultFechaToma.setText(dateInString)
        ResultFechaMuestra.setText(dateInString)


    }



    private fun setupListener(){
        ResultFechaToma.setOnClickListener{showDatePickerDialog()}
        ResultFechaMuestra.setOnClickListener{showDatePickerDialog()}
        ResultEvidencia.setOnClickListener { dispatchTakePictureIntent() }
    }

    //bitmap
    // extension function to get bitmap from assets
    fun Context.assetsToBitmap(fileName:String): Bitmap?{
        return try {
            val stream = assets.open(fileName)
            BitmapFactory.decodeStream(stream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    // extension function to convert bitmap to byte array
    fun Bitmap.toByteArray():ByteArray{
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG,10,this)
            return toByteArray()
        }
    }


    // extension function to convert byte array to bitmap
    fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this,0,size)
    }
    //base64
    fun Bitmap.toBase64String():String{
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG,10,this)
            return Base64.encodeToString(toByteArray(), Base64.DEFAULT)
        }
    }
    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    //Camera
    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            data?.extras?.let { bundle ->
                val imageBitmap = bundle.get("data") as Bitmap
                imageFoto.setImageBitmap(imageBitmap)
            }
        }
    }



    //DatePicker
    private fun showDatePickerDialog(){
        val datepicker= DatePickerFragment({ day, month, year -> onDateSelected(day, month, year)})
        datepicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        ResultFechaToma.setText("$year-${month+1}-$day")
        ResultFechaMuestra.setText("$year-${month+1}-$day")
    }
    //Consultar fecha actual
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}