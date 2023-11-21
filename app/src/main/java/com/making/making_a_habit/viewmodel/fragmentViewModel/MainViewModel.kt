package com.making.making_a_habit.viewmodel.fragmentViewModel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    //MainViewModel 내용 이곳으로 가져오기
    /*
    class MainViewModel (application: Application) : AndroidViewModel(application){

        private val repository = HabitRepository(application)

        suspend fun getAll(): List<Habit> {
            return withContext(viewModelScope.coroutineContext) {
                repository.getAll()
            }
        }
    }
    */
}