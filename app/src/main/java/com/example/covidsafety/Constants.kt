package com.example.covidsafety

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap


object Constants
{
    const val TOTAL_QUESTION:String="total_question"
    const val TOTAL_SCORE:String="total_score"
    const val USERS: String = "users"
    const val READ_STORAGE_PERMISSION_CODE = 1
    // A unique code of image selection from Phone Storage.
    private const val PICK_IMAGE_REQUEST_CODE = 2
    const val IMAGE: String = "image"
    const val NAME: String = "name"
    const val MOBILE: String = "mobile"
    fun getQuestions(): ArrayList<questions>{
        val questionsList=ArrayList<questions>()
        val question1 = questions(
            1,
            "Do you have fever?",
            "YES",
            "NO",
            1

        )
        questionsList.add(question1)

        val question2 = questions(
            2,
            "Do you have cold?",
            "YES",
            "NO",
            1

        )
        questionsList.add(question2)


        val question3 = questions(
            3,
            "Do you feel difficulty in breathing?",
            "YES",
            "NO",
            1

        )
        questionsList.add(question3)


        val question4 = questions(
            4,
            "Do you feel pain in your joints?",
            "YES",
            "NO",
            1

        )
        questionsList.add(question4)


        val question5 = questions(
            5,
            "Did you loss your Sense of Smell or Taste",
            "YES",
            "NO",
            1

        )
        questionsList.add(question5)


        val question6 = questions(
            6,
            "Do you have Diabetes?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question6)
        val question7 = questions(
            7,
            "Do you have Hypertension?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question7)

        val question8 = questions(
            8,
            "Do you have Lung Disease?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question8)

        val question9 = questions(
            9,
            "Do you have Heart Disease?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question9)

        val question10 = questions(
            10,
            "Do you have Kidney Disorder?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question10)
        val question11 = questions(
            11,
            "Did you travel in last 7-21 Days?",
            "YES",
            "NO",
            1
        )
        questionsList.add(question11)

        val question12 = questions(
            12,
            "Did you recently interacted or Lived with someone whose positive for COVID-19",
            "YES",
            "NO",
            1
        )
        questionsList.add(question12)

        return questionsList
    }
    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    /**
     * A function to get the extension of selected image.
     */
    fun getFileExtension(activity: Activity, uri: Uri?): String? {

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}