package com.example.fitnessapp.utils

import android.app.AlertDialog
import android.content.Context
import com.example.fitnessapp.R

object DialogManager {


    fun showDialog(context:Context,mId:Int,listener:Listener){
        var dialog:AlertDialog?=null
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.alert)
        builder.setMessage(mId)
        builder.setPositiveButton(R.string.reset){_,_->
            listener.onClick()
            dialog?.dismiss()
        }
        builder.setNegativeButton(R.string.back,){_,_->

        }
        dialog=builder.create()
        dialog.show()
    }

    interface Listener{
        fun onClick()
    }
}