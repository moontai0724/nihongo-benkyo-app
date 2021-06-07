package tw.edu.pu.nihongo_benkyo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tw.edu.pu.nihongo_benkyo.databinding.ActivityLauncherBinding

class Launcher : AppCompatActivity() {
    private lateinit var bind: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        bind.start.setOnClickListener {
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }
    }


}