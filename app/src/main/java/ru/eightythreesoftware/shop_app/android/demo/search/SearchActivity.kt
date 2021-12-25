package ru.eightythreesoftware.shop_app.android.demo.search

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import android.content.ComponentName
import ru.eightythreesoftware.shop_app.android.demo.R

class SearchActivity : AppCompatActivity(){

    private var searchQuery: Boolean = intent.getBundleExtra(SearchManager.APP_DATA)?.getBoolean("SEARCH") ?: false

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
            .inflate(R.menu.products_list_fragment_search_menu, menu)
        val searchManager = getSystemService(
            Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.menu.products_list_fragment_search_menu)
            ?.actionView as SearchView)
            .setSearchableInfo(
                searchManager.getSearchableInfo( this.componentName)
            )
        return true
    }

    override fun onSearchRequested(): Boolean {
        return super.onSearchRequested()
    }

    private fun searchQuery(query: String) {

    }
}