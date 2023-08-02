package id.maasrahman.mt_api

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.maasrahman.mt_api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductAdapter
    private var listData = mutableListOf<Product>()
    private var filterData = mutableListOf<Product>()

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Result"

        initView()
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        loadData()
    }

    private fun loadData(){
        apiInterface.getProduct().enqueue(object: Callback<ProductResponse?> {
            override fun onResponse(
                call: Call<ProductResponse?>,
                response: Response<ProductResponse?>
            ) {
                response.body()?.products?.let { rows ->
                    listData = rows.toMutableList()
                    adapter.updateData(listData)
                }
            }

            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                Toast.makeText(baseContext, t.message.toString(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    private fun initView(){
        with(binding){
            adapter = ProductAdapter { prod ->
                val intent = Intent(baseContext, ResultActivity::class.java)
                intent.putExtra("product", prod)
                startActivity(intent)
            }
            recyclerList.layoutManager = LinearLayoutManager(baseContext)
            recyclerList.adapter = adapter

            etSearch.addTextChangedListener(textListener)

            btnAdd.setOnClickListener {
                startActivity(Intent(baseContext, AddActivity::class.java))
            }
        }
    }

    private val textListener = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            filterData.clear()
            if(s?.isNotEmpty() == true){
                val tmpData = listData.filter { e -> e.title?.lowercase()?.contains(s.toString().lowercase()) == true } ?: emptyList()
                filterData.addAll(tmpData)
            }else{
                filterData.addAll(listData ?: emptyList())
            }
            adapter.updateData(filterData)
        }
    }
}