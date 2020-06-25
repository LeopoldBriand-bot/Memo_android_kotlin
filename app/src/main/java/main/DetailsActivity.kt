package main

import fragment.DetailsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import main.R

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val memoName = intent.getStringExtra("memoName")
        val memoDescription = intent.getStringExtra("memoDescription")
        // val memoStatus = intent.getBooleanExtra("memoStatus", false)
        val fragment = DetailsFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        bundle.putString("memoName", memoName)
        bundle.putString("memoDescription", memoDescription)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentParent, fragment, "details")
        fragmentTransaction.commit()
    }
}