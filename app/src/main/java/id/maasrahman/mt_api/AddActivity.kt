package id.maasrahman.mt_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import id.maasrahman.mt_api.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = "Input Product"

        with(binding){
            btnSubmit.setOnClickListener {
                if(etProductName.text.toString().isEmpty()){
                    etProductName.error = "Wajib diisi"
                    return@setOnClickListener
                }
                if(etProductDesc.text.toString().isEmpty()){
                    etProductDesc.error = "Wajib diisi"
                    return@setOnClickListener
                }

                val prod = Product(
                    title = etProductName.text.toString(),
                    description = etProductDesc.text.toString()
                )

                finish()
            }
        }
    }
}