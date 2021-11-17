package com.fundtastic.mymovie.compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class ViewModelActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected inline fun <reified VM : ViewModel>
    injectViewModels(): Lazy<VM> = viewModels { viewModelFactory }

  protected inline fun <reified T : ViewDataBinding> binding(
    @LayoutRes resId: Int
  ): T = DataBindingUtil.setContentView(this, resId)
}
