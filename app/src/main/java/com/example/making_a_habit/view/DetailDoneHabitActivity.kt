package com.example.making_a_habit.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.PixelCopy
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.DetailsDonehabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DetailDonehabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DetailDoneHabitActivity : AppCompatActivity() {

    /***** ViewModel *****/
    val detaildonehabitViewModel: DetailDonehabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsDonehabitPageBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var habit : Habit

        /***** veiwBinding *****/
        binding = DetailsDonehabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnDetailsdonehabitpage.setOnClickListener {
            val intent = Intent(this, ListDoneHabitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }



        /***** 데이터 받고 해당 화면에 출력하기 *****/
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            val habitId = intent.getIntExtra("data",0)
            println(habitId) // 잘 나오는지 확인
            CoroutineScope(Dispatchers.IO).launch {
                println(detaildonehabitViewModel.getHabitId(intent.getIntExtra("data", 0)))
                habit = detaildonehabitViewModel.getHabitId(intent.getIntExtra("data", 0))
                println(habit.habitName)

                runOnUiThread {
                    binding.habitNameTextDetailshabitpage.text = habit.habitName  // habitName
                    val habitDate = habit.habitDateStart + " ~ " + habit.habitDateEnd
                    binding.habitDateTextDetailshabitpage.text = habitDate  // habitDateStart ~ habitDateEnd
                    when(habit.habitColor){  // habitColor theme 설정
                        "red" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_red)
                        }
                        "yellow" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_yellow)
                        }
                        "green" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1E6E6"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_green)
                        }
                        "blue" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_blue)
                        }
                        "gray" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#CECECE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_gray)
                        }
                    }
                    val roundfull = habit.habitRoundFull.toString() + " / " + habit.habitPeriodNum.toString()
                    binding.roundfullTextDetailshabitpage.text = roundfull  // habitRoundFull / habitPeriodNum
                    binding.habitCommentTextDetailshabitpage.text = habit.habitComment  // habitComment
                    if(habit.habitComment == "이 습관은 커멘트가 작성되지 않았습니다."){  // habitComment 작성 안할 시 내용 보여주고 text 색 변경
                        binding.habitCommentTextDetailshabitpage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                    }
                }
            }
        }

        /***** dialog 부분 *****/
        binding.deleteBtnDetailsdonehabitpage.setOnClickListener{
            val deletedialog = deleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            val bundle = Bundle()
            bundle.putInt("deleteId", habitId)
            deletedialog.arguments = bundle

            deletedialog.show(supportFragmentManager, "deleteDialog")
        }

        /** ....공유하기 기능..... **/
        binding.shareBtnDetailsdonehabitpage.setOnClickListener{
            getBitmap(binding.shareLayout){ bitmap ->
                screenToShare(bitmap)
            }
        }

    } // OnCreat


    /** ....공유하기 기능..... **/
    private fun getBitmap(view: View, callback: (Bitmap?) -> Unit) {
        window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(window,
                    Rect(locationOfViewInWindow[0], locationOfViewInWindow[1], locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height),
                    bitmap, { copyResult ->
                        if (copyResult == PixelCopy.SUCCESS) callback.invoke(bitmap)
                        else callback.invoke(null)
                    }, Handler(Looper.getMainLooper())
                )
            } catch (e: IllegalArgumentException) {
                callback.invoke(null)
            }
        }
    }

    /** ....공유하기 기능..... **/
    private fun screenToShare(bitmap: Bitmap?) {
        try {
            val cachePath = File(cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/image.png")
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
            val newFile = File(cachePath, "image.png")
            val contentUri = FileProvider.getUriForFile( this, "com.example.making_a_habit", newFile)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_STREAM, contentUri)

            /*
            val resInfoList: List<ResolveInfo> = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                grantUriPermission(
                    packageName,
                    contentUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
             */

            startActivity(Intent.createChooser(intent, "Share image"))
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, ListDoneHabitActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }
}