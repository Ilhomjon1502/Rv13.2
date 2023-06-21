package uz.ilhomjon.rv132

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import uz.ilhomjon.rv132.adapter.UserAdapter
import uz.ilhomjon.rv132.databinding.ActivityMainBinding
import uz.ilhomjon.rv132.models.User
import uz.ilhomjon.rv132.utils.MySharedPreference

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddAndEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userAdapter = UserAdapter(MySharedPreference.list, object :UserAdapter.RvAction{
            override fun deleteUser(user: User, position: Int) {
                val list = MySharedPreference.list
                list.removeAt(position)
                MySharedPreference.list = list
                onResume()
            }

            override fun editUser(position: Int) {
                val intent = Intent(this@MainActivity, AddAndEditActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })

        binding.rv.adapter = userAdapter

    }
}