package uk.co.getthenumbers.android.a50petitiontracker.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import uk.co.getthenumbers.android.a50petitiontracker.R
import java.text.NumberFormat
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val numberFormat = NumberFormat.getInstance()

    private lateinit var txtCount: TextView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        txtCount = findViewById(R.id.txtCount)
        progress = findViewById(R.id.progress)
        val btnSign = findViewById<Button>(R.id.btnSign)
        val btnShare = findViewById<Button>(R.id.btnShare)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)
        viewModel.getCount().observe(this, Observer(this::populateCount))

        btnSign.setOnClickListener {
            doSign()
        }

        btnShare.setOnClickListener {
            doShare()
        }
    }

    private fun populateCount(count: Int?) {
        count?.let {
            txtCount.text = numberFormat.format(it)
            progress.visibility = View.GONE
            txtCount.visibility = View.VISIBLE
        }
    }

    private fun doSign() {
        val petitionUrl = Uri.parse(getString(R.string.main_activity_petition_url))
        Intent(Intent.ACTION_VIEW)
                .setData(petitionUrl)
                .also {
                    try {
                        startActivity(it)
                    } catch (ignored: ActivityNotFoundException) {

                    }
                }
    }

    private fun doShare() {
        Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.main_activity_share_text))
                .setType("text/plain")
                .also {
                    startActivity(Intent.createChooser(it,
                            getString(R.string.main_activity_share_dialog_title)))
                }
    }
}
