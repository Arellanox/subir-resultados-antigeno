package com.example.refineria.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.refineria.R

class SupervisionesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container : ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.fragment_supervisiones, container, false)
    }

}