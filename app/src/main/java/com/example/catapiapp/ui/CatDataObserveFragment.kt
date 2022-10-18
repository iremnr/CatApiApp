package com.example.catapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.catapiapp.R
import com.example.catapiapp.base.BaseFragment
import com.example.catapiapp.databinding.FragmentCatDataObserveBinding
import com.example.catapiapp.model.Cat
import com.example.catapiapp.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatDataObserveFragment : BaseFragment<FragmentCatDataObserveBinding, ObserveViewModel>(
    FragmentCatDataObserveBinding::inflate
) {
    override val viewModel by viewModels<ObserveViewModel>() //view model' ın bağlanması

    override fun onCreateFinished() {
        viewModel.getData()
        binding.lifecycleOwner = this
    }

    override fun initializeListeners() {
        binding.ImageButton.setOnClickListener {
            viewModel.getData()
        }
    }

    override fun observeEvents() {
        viewModel.cat.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            it?.let {
                Toast.makeText(requireContext(),it.url,Toast.LENGTH_LONG).show()
                binding.imageView5.loadImage(it.url)

            }
        })
        with(viewModel){
            isLoading.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
                handleView(it)
            })
        }
    }

    private fun handleView(isLoading: Boolean = false) {
        binding.detailGroup.isVisible = !isLoading
        binding.pbDetail.isVisible = isLoading
    }
}