package com.example.refineria

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.refineria.classes.Frentes
import com.example.refineria.database.SQLite
import com.example.refineria.fragments.*
import com.example.refineria.sharedpreference.RefineriaApplication
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private val perfilFragment = PerfilFragment()
    private val antigenoFragment = Antigeno()
    private val supervisionesFragment = SupervisionesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //checkUserValues()



        replaceFragment(antigenoFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_perfil -> replaceFragment(perfilFragment)
                R.id.ic_home-> replaceFragment(antigenoFragment)
                R.id.ic_search -> replaceFragment(supervisionesFragment)

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }


}