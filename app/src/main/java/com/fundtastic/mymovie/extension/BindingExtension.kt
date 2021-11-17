package com.fundtastic.mymovie.extension

import android.view.View
import android.widget.Toast
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Status

inline fun <reified T> View.bindResource(resource: Resource<T>?, onSuccess: (Resource<T>) -> Unit) {
  if (resource != null) {
    when (resource.status) {
      Status.LOADING -> Unit
      Status.SUCCESS -> onSuccess(resource)
      Status.ERROR ->
        Toast.makeText(context,
          resource.errorEnvelope?.status_message.toString(), Toast.LENGTH_SHORT).show()
    }
  }
}
