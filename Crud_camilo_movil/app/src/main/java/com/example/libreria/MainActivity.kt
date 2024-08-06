package com.example.libreria

import android.content.Intent
import android.content.res.Configuration
import  android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer

import com.google.android.material.navigation.DrawerLayoutUtils
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       /* val nav_item_one: Button = findViewById(R.id.nav_item_one)
        nav_item_one.setOnClickListener {
            val intent = Intent(this, lista_libro::class.java)
            startActivity(intent)
        }
*/


        val toolbar:Toolbar =findViewById(R.id.toolbar_main )
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navegation_drawer_open,R.string.navegation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView:NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener (this)

        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment:Fragment=lista_libroFragment()//fragment inicial
        when (item.itemId) {
            R.id.nav_item_one -> fragment=lista_libroFragment() //se llama al fragment seleccionado
            R.id.nav_item_two -> Toast.makeText(this,"Usario", Toast.LENGTH_SHORT).show()
            R.id.nav_item_three -> Toast.makeText(this,"Prestamo", Toast.LENGTH_SHORT).show()
            R.id.nav_item_four -> Toast.makeText(this,"Multa", Toast.LENGTH_SHORT).show()
        }

        val fragmentManager=
            supportFragmentManager
        val fragmentTransaction=
            fragmentManager.beginTransaction()
        fragmentTransaction.
        replace(R.id.containerFragment,fragment)
        fragmentTransaction.commit()

        drawer.closeDrawer(GravityCompat.START)
        return true

       }

    override fun onPostCreate(savedInstanceStat: Bundle?) {
        super.onPostCreate(savedInstanceStat)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    }
