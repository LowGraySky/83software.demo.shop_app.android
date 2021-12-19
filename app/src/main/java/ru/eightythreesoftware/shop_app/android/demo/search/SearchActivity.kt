package ru.eightythreesoftware.shop_app.android.demo.search

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.eightythreesoftware.shop_app.android.demo.R

class SearchActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchQuery(query)
            }
        }
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL)
    }

    override fun onSearchRequested(): Boolean {
        return super.onSearchRequested()
    }

    private fun searchQuery(query: String) {

    }
}