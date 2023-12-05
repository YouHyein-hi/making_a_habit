package com.making.making_a_habit.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.making.making_a_habit.R
import com.making.making_a_habit.databinding.ItemRoundfullBinding
import com.example.domain.model.DetailItem
import com.example.data.entity.HabitEntity
import com.making.making_a_habit.ui.fragment.DetailFragment
import java.time.LocalDate

class DetailAdapter(var fragment: DetailFragment.getAdapterData)
    : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {


    private lateinit var onMainItemClick : (HabitEntity) -> Unit
    private lateinit var binding : ItemRoundfullBinding
    private val todayDate: LocalDate = LocalDate.now()
    private val list = arrayListOf<Int>()
    private var periodNum = 0
    private var color = ""
    private var period = ""
    private var dateIng = ""
    private var dateEnd = ""
    private var roundfull = 0
    private var lastround = 0

    inner class ViewHolder(private val binding: ItemRoundfullBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(count_bind: Int) {

            /***** roundbutton 기본 설정 *****/
            when(color){
                "red" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_red)
                "yellow" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_yellow)
                "green" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_green)
                "blue" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_blue)
                "gray" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_gray)
            }
            binding.habitroundfull.text = count_bind.toString()
            binding.habitroundfull.isEnabled = false
            binding.habitroundfull.isClickable = false
            if (!binding.habitroundfull.isEnabled){
                binding.habitroundfull.setTextColor(Color.parseColor("#b1b1b1"))
            }
            /*** 체크리스트에 아무것도 체크 안되어 있을 경우 1번칸만 활성화 ***/
            if(lastround == 0){
                if(position == 0){
                    binding.habitroundfull.isEnabled = true
                    binding.habitroundfull.isClickable = true
                    binding.habitroundfull.setTextColor(Color.parseColor("#000000"))
                }
            }

            /** DateIng < habitDateToday일 경우 lastRound+1 Button 활성화 **/
            val dateIngInt = dateIng.replace("-", "").toInt()
            val dateTodayInt = todayDate.toString().replace("-", "").toInt()
            if(lastround != 0){
                if (dateIngInt < dateTodayInt){
                    if(position == (lastround)){
                        binding.habitroundfull.isEnabled = true
                        binding.habitroundfull.isClickable = true
                        binding.habitroundfull.setTextColor(Color.parseColor("#000000"))
                    }
                }
            }

            /*** habitLastRoundFull만큼 체크리스트 채우기 ***/
            for (i in 0..lastround){
                if(i == position){
                    if(i < lastround){
                        binding.habitroundfull.isSelected = true
                        binding.habitroundfull.isClickable = false
                        when(color){
                            "red" -> binding.habitroundfull.setTextColor(Color.parseColor("#d99494"))
                            "yellow" -> binding.habitroundfull.setTextColor(Color.parseColor("#d9c594"))
                            "green" -> binding.habitroundfull.setTextColor(Color.parseColor("#97b0b2"))
                            "blue" -> binding.habitroundfull.setTextColor(Color.parseColor("#94b8d9"))
                            "gray" -> binding.habitroundfull.setTextColor(Color.parseColor("#919191"))
                        }
                    }
                }
            }

            /*** 칸 클릭 이벤트 ***/
            /** habitRoundFull+1, habitLastRoundFull+1, DateIng에는 오늘 날짜 저장   getData 불러와서 UPDATE **/
            binding.habitroundfull.setOnClickListener{
                binding.habitroundfull.isSelected = true
                binding.habitroundfull.isClickable = false
                roundfull += 1
                lastround += 1
                dateIng = todayDate.toString()
                println("roundfull : " + roundfull + ", lastround : " + lastround + ", dateIng : " + dateIng)
                Log.e("DetailAdapter", "bind: roundfull-${roundfull}, lastround-${lastround}, dateIng-${dateIng}", )
                fragment.updateData(dateIng, roundfull, lastround)
                when(color){
                    "red" -> binding.habitroundfull.setTextColor(Color.parseColor("#d99494"))
                    "yellow" -> binding.habitroundfull.setTextColor(Color.parseColor("#d9c594"))
                    "green" -> binding.habitroundfull.setTextColor(Color.parseColor("#97b0b2"))
                    "blue" -> binding.habitroundfull.setTextColor(Color.parseColor("#94b8d9"))
                    "gray" -> binding.habitroundfull.setTextColor(Color.parseColor("#919191"))
                }

                /*
                /*** 완료 이벤트 ***/
                if(period == "횟수"){
                    if(lastround == periodNum) {
                        Log.e("둘이", " 같음!")
                        activity.getData(dateIng, roundfull, lastround)
                        activity.completeDialog()
                    }
                    else Log.e("둘이", " 같지 않음!")
                }
                else if(period == "기간"){
                    if(dateEnd == habitDateToday.toString()) {
                        activity.getData(dateIng, roundfull, lastround)
                        activity.completeDialog()
                    }
                    else if(lastround == periodNum){
                        activity.getData(dateIng, roundfull, lastround)
                        activity.completeDialog()
                    }
                    else Log.e("둘이", " 같지 않음!")
                }
                */
            }

            /*
            /*** 완료 이벤트 ***/
            /** 기간일 경우  habitDateEnd을 넘겼을 경우 완료 이벤트 **/
            if(period == "기간"){
                dateEnd = dateEnd.replace("-", "")
                val dateToday = habitDateToday.toString().replace("-", "")
                if(dateEnd.toInt() < dateToday.toInt()){
                    activity.getData(dateIng, roundfull, lastround)
                    activity.completeDialog()
                }
            }*/
            // 위 완료 이벤트는 DetailFragment로 옮겨서 없애도 될 듯!

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.ViewHolder {
        binding = ItemRoundfullBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DetailAdapter.ViewHolder, position: Int) {
        viewHolder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabitPeriod(item: DetailItem) {
        item.count?: return
        item.color?: return
        item.period?: return
        item.dateIng?: return
        item.dateEnd?: return
        item.roundfull?: return
        item.lastround?: return

        periodNum = item.count!!
        color = item.color!!
        period = item.period!!
        dateIng = item.dateIng!!
        dateEnd = item.dateEnd
        roundfull = item.roundfull!!
        lastround = item.lastround!!
        list.clear()
        for (i in 1..item.count!!){
            list.add(i)
        }
        notifyDataSetChanged()
    }


}