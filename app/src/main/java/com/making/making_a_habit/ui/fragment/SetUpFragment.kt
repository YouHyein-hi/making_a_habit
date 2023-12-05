package com.making.making_a_habit.ui.fragment

import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentSetUpBinding


class SetUpFragment : BaseFragment<FragmentSetUpBinding>(FragmentSetUpBinding::inflate, "SetUpFragment") {

    private lateinit var callback: OnBackPressedCallback
    /*
    /***** 푸시알림 부분 *****/
    private val manager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    private lateinit var pIntent: PendingIntent*/

    override fun initData() {
    }

    override fun initUi() {
    }

    override fun initListener() {
        with(binding){

            backBtnSetuppage.setOnClickListener {
                findNavController().popBackStack()
            }

            opensourceLayout.setOnClickListener {
                startActivity(Intent(requireActivity(), OssLicensesMenuActivity::class.java))
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
            }


            /*
            /***** switch 상태 확인 후 switchbutton 상태 변경 *****/
            val sharedPreference =  getSharedPreferences("switch_state",Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            val switch_state = sharedPreference.getBoolean("switch_state", false)
            if(switch_state == false){
                Log.e("Is switch state?", "false")
                binding.alarmSwitch.setChecked(false)
                //cancelAlarm()
            }
            else{
                Log.e("Is switch state?", "true")
                binding.alarmSwitch.setChecked(true)
                //setPushNotification()
            }

            /***** switchbutton 클릭시 상태 변경 및 데이터 변경 *****/
            binding.alarmSwitch.setOnCheckedChangeListener{CompoundButton, onSwitch ->
                /** switch 켜짐 **/
                if (onSwitch){
                    Log.e("푸시알림 스위치 상태 : ", onSwitch.toString())
                    //setPushNotification()
                    editor.putBoolean("switch_state", true)
                    editor.apply()
                }
                /** switch 꺼짐 **/
                else{
                    Log.e("푸시알림 스위치 상태 : ", onSwitch.toString())
                    //cancelAlarm()
                    editor.putBoolean("switch_state", false)
                    editor.apply()
                }
            }
            */

        }
    }

    override fun initObserver() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        /*
        /***** 푸시알림 *****/
        @SuppressLint("ShortAlarm")
        private fun setPushNotification(){
            pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
                putExtra("data", "00")
            }.let {
                PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
            }

            /*val calendar = Calendar.getInstance()
            if(Calendar.getInstance().after(calendar)){
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            manager.setRepeating(AlarmManager.RTC, setBaseTime(20).timeInMillis, AlarmManager.INTERVAL_DAY, pIntent)*/
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setBaseTime(3).timeInMillis, AlarmManager.INTERVAL_DAY, pIntent)
            /*print(setBaseTime(12).toString())*/
            //Log.e("호이 : ", setBaseTime(12).toString())
        }

        /*** 푸시알림 시간 설정 ***/
        private fun setBaseTime(baseHour: Int): Calendar {
            val today = LocalDate.now()
            val todayCalendar = Calendar.getInstance()
            val baseTime = Calendar.getInstance().apply {
                set(today.year, today.monthValue -1, today.dayOfMonth, baseHour, 1)
                /*set(Calendar.YEAR, today.year)
                set(Calendar.MONTH, today.monthValue-1)
                set(Calendar.DATE, today.dayOfMonth)
                set(Calendar.HOUR_OF_DAY, baseHour)
                set(Calendar.MINUTE, 30)*/
            }

            Log.e("baseHour :", baseHour.toString())

            return if (todayCalendar.time.time < baseTime.time.time) {
                Calendar.getInstance().apply {
                    set(today.year, today.monthValue -1 , today.dayOfMonth - 1, baseHour, 1)
                    /*set(Calendar.YEAR, today.year)
                    set(Calendar.MONTH, today.monthValue-1)
                    set(Calendar.DATE, today.dayOfMonth-1)
                    set(Calendar.HOUR_OF_DAY, baseHour)
                    set(Calendar.MINUTE, 30)*/
                }
            } else {
                baseTime
            }
        }
        /*** 푸시알림 취소 ***/
        private fun cancelAlarm() {
            val receiverIntent = Intent(getApplication(), AlarmBroadCastReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)
            manager.cancel(pendingIntent)
        }
        */

    }


}