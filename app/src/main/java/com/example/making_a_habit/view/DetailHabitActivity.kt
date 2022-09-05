package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.databinding.DetailsHabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.adapter.Habit15RoundAdapter
import com.example.making_a_habit.view.adapter.Habit30RoundAdapter
import com.example.making_a_habit.view.adapter.Habit3RoundAdapter
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DetailhabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailHabitActivity : AppCompatActivity()  {

    /***** ViewModel *****/
    val detailhabitViewModel: DetailhabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsHabitPageBinding
    /***** Adapter *****/
    private val habit3RoundAdapter: Habit3RoundAdapter =  Habit3RoundAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }
    private val habit15RoundAdapter: Habit15RoundAdapter =  Habit15RoundAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }
    private val habit30RoundAdapter: Habit30RoundAdapter =  Habit30RoundAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var habit : Habit
        var habitDateStart : String

        /***** veiwBinding *****/
        binding = DetailsHabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnDetailshabitpage.setOnClickListener{
            finish()
        }


        // MainRecyclerViewAdapter에서 해당 item 데이터 habitId 받음
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            CoroutineScope(Dispatchers.IO).launch {
                println(detailhabitViewModel.loadAllByIds(intent.getIntExtra("data", 0)))
                habit = detailhabitViewModel.loadAllByIds(intent.getIntExtra("data", 0))
                println(habit.habitName)

                runOnUiThread {
                    binding.habitNameTextDetailshabitpage.text = habit.habitName
                    when(habit.habitColor){
                        "red" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                        "yellow" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                        "green" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1CFD1"))
                        "blue" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                        "gray" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#CECECE"))
                    } // TODO Color 지정해준 거 나중에 변경하기 (R.color.theme_?)이 안되는지 모르겠음


                    if(habit.habitPeriod == "기간"){
                        println("기간이 넘어옴!")
                        habitDateStart = habit.habitDateStart + " ~ " + habit.habitDateEnd
                        binding.habitDateTextDetailshabitpage.text = habitDateStart
                    }
                    else if(habit.habitPeriod == "횟수"){
                        println("횟수가 넘어옴!")
                        habitDateStart = habit.habitDateStart + " ~"
                        binding.habitDateTextDetailshabitpage.text = habitDateStart
                    }


                    if(habit.habitPeriodNum == 3){
                        binding.detailshabitpageRecyclerView.adapter = habit3RoundAdapter //리사이클러뷰에 어댑터 연결
                        binding.detailshabitpageRecyclerView.layoutManager = LinearLayoutManager(this@DetailHabitActivity)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        habit3RoundAdapter.sethabitPeriod()
                        println("habit.habitPeriodNum == 3")
                    }
                    else if(habit.habitPeriodNum == 15){
                        binding.detailshabitpageRecyclerView.adapter = habit15RoundAdapter //리사이클러뷰에 어댑터 연결
                        binding.detailshabitpageRecyclerView.layoutManager = LinearLayoutManager(this@DetailHabitActivity)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        habit15RoundAdapter.sethabitPeriod()
                        println("habit.habitPeriodNum == 15")
                    }
                    else if(habit.habitPeriodNum == 30){
                        binding.detailshabitpageRecyclerView.adapter = habit30RoundAdapter //리사이클러뷰에 어댑터 연결
                        binding.detailshabitpageRecyclerView.layoutManager = LinearLayoutManager(this@DetailHabitActivity)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        habit15RoundAdapter.sethabitPeriod()
                        println("habit.habitPeriodNum == 30")
                    }
                }
            }
        }


        /***** dialog 부분 *****/
        binding.deleteBtnDetailshabitpage.setOnClickListener{
            var deletedialog = deleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            println("habitId : " + habitId)
            val bundle = Bundle()
            bundle.putInt("deleteId", habitId)
            deletedialog.arguments = bundle
            println("보냄")


            deletedialog.show(supportFragmentManager, "deleteDialog")
        }

        /***** ViewModel 부분 *****/
//        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
//            // update UI
//            //adapter.
//        })


        /* TODO 임시!!!!!! 나중에 꼭 삭제하기!!!!!!!!!! */
        binding.testButton.setOnClickListener{
            val habitId = intent.getIntExtra("data",0)
            var intent = Intent(this, CommentActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("commentId", habitId)
            startActivity(intent)
            finish()
        }

    }  // onCreate

    fun deletebundle(fragment: deleteDialogFragment){
        val habitId = intent.getIntExtra("data",0)
        val bundle = Bundle()

        bundle.putInt("deleteId", habitId)
        fragment.arguments = bundle
        setFragment(fragment)
    }

    fun setFragment(fragment:deleteDialogFragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.commit()
    }
}