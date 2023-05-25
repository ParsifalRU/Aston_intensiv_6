package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_ACTIVITY_STATE = "KEY ACTIVITY STATE"
        const val STATE_ON = "1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListFragment(savedInstanceState)
    }

    private fun setListFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState?.getString(KEY_ACTIVITY_STATE) == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, ListFragment())
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_ACTIVITY_STATE, STATE_ON)
    }
}