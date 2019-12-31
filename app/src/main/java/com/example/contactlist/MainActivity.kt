package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sqlitesimplekotlin.DatabaseHelper
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.new_contact.*
import kotlinx.android.synthetic.main.new_contact.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var databaseHelper: DatabaseHelper? = null
    private var arrayList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)


        //button click to show dialog
        mainLoginBtn.setOnClickListener {

            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.new_contact, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Adding new contact")
            //show dialog
            val mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.addContact.setOnClickListener {
                val firstName = mDialogView.firstName.text.toString()
                val lastName = mDialogView.lastName.text.toString()
                val homeNumber = mDialogView.homeNumber.text.toString()
                val workNumber = mDialogView.workNumer.text.toString()
                //dismiss dialog
                if (firstName.isEmpty()) {
                    Toast.makeText(this, "Enter First Name!", Toast.LENGTH_SHORT).show()
                } else if (homeNumber.isEmpty()) {
                    Toast.makeText(this, "Enter Home number!", Toast.LENGTH_SHORT).show()
                } else {
                    mAlertDialog.dismiss()
                    //get text from EditTexts of custom layout

                    //set the input text in TextView
//                    mainInfoTv.setText(firstName + "\n" + lastName + "\n " + homeNumber + "\n" + workNumber)
                    databaseHelper!!.addStudentDetail(firstName, lastName, homeNumber, workNumber)

                    Toast.makeText(this, "Stored Successfully!", Toast.LENGTH_SHORT).show()
                }
            }

            //cancel button click of custom layout
            mDialogView.cancel.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }

        }

        databaseHelper = DatabaseHelper(this)


        show.setOnClickListener {
            arrayList = databaseHelper!!.allContactsList
            mainInfoTv!!.text = ""
            mainInfoTv!!.text = arrayList.toString()



        }
    }
}