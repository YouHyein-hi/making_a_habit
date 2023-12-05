package com.making.making_a_habit.ui.fragment

import android.graphics.Color
import android.util.TypedValue
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentHistoryDetailBinding
import com.making.making_a_habit.dataClass.DetailData
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.HistoryDetailViewModel

class HistoryDetailFragment : BaseFragment<FragmentHistoryDetailBinding>(FragmentHistoryDetailBinding::inflate, "HistoryDetailFragment") {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val vieModel : HistoryDetailViewModel by viewModels()
    private lateinit var habitData : DetailData


    override fun initData() {
        habitData = mainViewModel.selectedData.value!!
    }

    override fun initUi() {
        with(binding){

            habitNameTextDetailshabitpage.text = habitData.name  // habitName
            val habitDate = habitData.dateStart + " ~ " + habitData.dateEnd
            habitDateTextDetailshabitpage.text = habitDate  // habitDateStart ~ habitDateEnd
            when(habitData.color){  // habitColor theme 설정
                "red" -> {
                    habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                    roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_red)
                }
                "yellow" -> {
                    habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                    roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_yellow)
                }
                "green" -> {
                    habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1E6E6"))
                    roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_green)
                }
                "blue" -> {
                    habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                    roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_blue)
                }
                "gray" -> {
                    habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AAAAAA"))
                    roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_gray)
                }
            }
            val roundfull = habitData.roundFull.toString() + " / " + habitData.periodNum.toString()
            roundfullTextDetailshabitpage.text = roundfull  // habitRoundFull / habitPeriodNum
            habitCommentTextDetailshabitpage.text = habitData.comment  // habitComment
            if(habitData.comment == "이 습관은 커멘트가 작성되지 않았습니다."){  // habitComment 작성 안할 시 내용 보여주고 text 색 변경
                habitCommentTextDetailshabitpage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            }

        }
    }

    override fun initListener() {
        with(binding){

            backBtnDetailsdonehabitpage.setOnClickListener {
                findNavController().popBackStack()
            }

            deleteBtnDetailsdonehabitpage.setOnClickListener {
                // TODO 삭제 dialog 띄우기
            }

            shareBtnDetailsdonehabitpage.setOnClickListener {
                // TODO 공유하기 기능 추가하기
                /*getBitmap(shareLayout){ bitmap ->
                    screenToShare(bitmap)
                }*/
            }

        }
    }

    override fun initObserver() {
    }


    /*
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
            val contentUri = FileProvider.getUriForFile( this, "com.making.making_a_habit", newFile)
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
     */

}