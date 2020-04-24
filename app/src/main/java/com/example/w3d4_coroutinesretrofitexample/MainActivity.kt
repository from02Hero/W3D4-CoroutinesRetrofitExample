package com.example.w3d4_coroutinesretrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w3d4_coroutinesretrofitexample.network.ApiClient
import com.example.w3d4_coroutinesretrofitexample.network.DataModel
import com.example.w3d4_coroutinesretrofitexample.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private val tag : String = MainActivity::class.java.simpleName

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Main
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val repository : Repository = Repository(ApiClient.getClient)

    var dataList = mutableListOf<DataModel>()
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    fun getLatestData() {
        ///launch the coroutine scope
        scope.launch {
            //get latest data from repo
            indeterminateBar.visibility = View.VISIBLE
            dataList = repository.getData()!!
            adapter.updateWords(dataList)
            indeterminateBar.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        coroutineContext.cancel()
    }

    fun initRecycler() {
        recyclerView = findViewById(R.id.recycler_view)

        //setting up the adapter
        adapter = DataAdapter(dataList) { dataModel : DataModel -> itemClicked(dataModel) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        getLatestData()
    }

    private fun itemClicked(dataModel: DataModel) {
        Toast.makeText(this, "Clicked: ${dataModel.title}", Toast.LENGTH_LONG).show()
    }
}
