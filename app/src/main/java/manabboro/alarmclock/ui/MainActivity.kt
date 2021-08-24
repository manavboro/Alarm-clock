package manabboro.alarmclock.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import manabboro.alarmclock.R
import manabboro.alarmclock.ViewModelFactory
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.databinding.ActivityMainBinding
import manabboro.alarmclock.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val typeFace = Typeface.createFromAsset(assets, "fonts/rubik_regular.ttf")
        mBinding.alarmTitle.typeface = typeFace

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainAdapter(this)
        mBinding.recyclerView.adapter = mAdapter

        mBinding.fab.setOnClickListener {
            val intent = Intent(this, AddAlarmActivity::class.java)
            startActivity(intent)
        }

        val alarmRepository = AlarmRepository(this)
        val factory = ViewModelFactory.Companion.MainViewModelFactory(alarmRepository)
        mViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mViewModel.getAlarmsLiveData().observe(this,
            {
                mAdapter.updateData(it)
            })
    }
}