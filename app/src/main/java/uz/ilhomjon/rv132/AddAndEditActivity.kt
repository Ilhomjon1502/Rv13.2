package uz.ilhomjon.rv132

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.ilhomjon.rv132.databinding.ActivityAddAndEditBinding
import uz.ilhomjon.rv132.models.User
import uz.ilhomjon.rv132.utils.MySharedPreference

class AddAndEditActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddAndEditBinding.inflate(layoutInflater) }
    var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)

        position = intent.getIntExtra("position", -1)

        if (position!=-1){
           editUser()
        }else{
            addUser()
        }
    }

    fun editUser(){
        val list = MySharedPreference.list
        binding.apply {
            edtName.setText(list[position].name)
            edtNumber.setText(list[position].number)

            btnSave.setOnClickListener {
                val user = User(edtName.text.toString(), edtNumber.text.toString())
                list[position] = user
                MySharedPreference.list=list
                Toast.makeText(this@AddAndEditActivity, "Changed", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun addUser(){
        binding.btnSave.setOnClickListener {
            val user = User(binding.edtName.text.toString(), binding.edtNumber.text.toString())
            val list = MySharedPreference.list
            list.add(user)
            MySharedPreference.list = list
            Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}