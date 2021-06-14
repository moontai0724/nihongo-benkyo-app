package tw.edu.pu.nihongo_benkyo

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tw.edu.pu.nihongo_benkyo.databinding.ActivityLauncherBinding
import tw.edu.pu.nihongo_benkyo.model.Repository

class Launcher : AppCompatActivity() {
    private lateinit var bind: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        bind.start.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.show()

            val repository = Repository(this)
            repository.update {
                progressDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}