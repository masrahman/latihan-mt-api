package id.maasrahman.mt_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import id.maasrahman.mt_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductAdapter
    private var listData = mutableListOf<Product>()
    private var filterData = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Result"

        addDummy()

        initView()
    }

    private fun addDummy(){
        listData.add(Product(title = "Product 1", description = "Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet", price = 45, thumbnail = "https://takeaseat.my/wp-content/uploads/2022/06/Z1180-Stripe-Light-Pink-School-Bag-3-400x400.jpg"))
        listData.add(Product(title = "Product 2", description = "Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet", price = 45, thumbnail = "https://takeaseat.my/wp-content/uploads/2022/06/Z1180-Stripe-Light-Pink-School-Bag-3-400x400.jpg"))
        listData.add(Product(title = "Product 3", description = "Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet", price = 45, thumbnail = "https://takeaseat.my/wp-content/uploads/2022/06/Z1180-Stripe-Light-Pink-School-Bag-3-400x400.jpg"))
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
                val tmpData = listData.filter { e -> e.title?.lowercase()?.contains(s.toString().lowercase()) == true }
                filterData.addAll(tmpData)
            }else{
                filterData.addAll(listData)
            }
            adapter.updateData(filterData)
        }
    }
}