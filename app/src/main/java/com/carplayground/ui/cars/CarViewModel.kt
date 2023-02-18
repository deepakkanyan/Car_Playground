package com.carplayground.ui.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carplayground.data.local.CarRepo
import com.carplayground.room.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor (private val repository: CarRepo) : ViewModel() {



    private var _carDetailsList = MutableLiveData<List<Car>>()
    val carDetailsList : LiveData<List<Car>> = _carDetailsList

    private var _isDataNeedToDownload =  MutableLiveData<Boolean>()
    val isDataNeedToDownload : LiveData<Boolean> = _isDataNeedToDownload

    private var _carDetailsFilteredList = MutableLiveData<List<Car>?>()
    val carDetailsFilteredList : LiveData<List<Car>?> = _carDetailsFilteredList

    init {
        getCarListFromDatabase()
    }


    private fun getCarListFromDatabase(){
        viewModelScope.launch {
          val cardList = repository.getCars()
         if(cardList.isEmpty()){
             _isDataNeedToDownload.value = true
         }
            _carDetailsList.value = cardList
            _carDetailsFilteredList.value = cardList

        }
    }


    fun saveDb(list : List<Car>){
        viewModelScope.launch {
            repository.insertCars(list)
        }
    }

    fun filterCarList(filter: String) {
        val filterList = _carDetailsList.value?.filter { (it.copy().model == filter) }
        _carDetailsFilteredList.value =  filterList
    }


}