package com.carplayground.ui.cars

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.carplayground.R
import com.carplayground.data.filesystem.FetchDataFromFile
import com.carplayground.room.Car
import com.carplayground.ui.component.BannerView
import com.carplayground.ui.component.BoxWithDropdowns
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CarListFragment : Fragment() {

     private val myViewModel: CarViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        return ComposeView(requireContext()).apply {
            setContent {
                InitializeView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeView()
    }

    private fun subscribeView() {
       myViewModel.isDataNeedToDownload.observe(viewLifecycleOwner) {
            if (it) {
                myViewModel.saveDb(FetchDataFromFile.fetchAndSaveData(requireContext()))
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InitializeView() {
        val list =  myViewModel.carDetailsList.observeAsState().value
        Scaffold { padding ->
            Box(Modifier.padding(padding)) {
                list?.let {carList->
                    Column {
                        BannerView()
                        BoxWithDropdowns(carList){
                            myViewModel.filterCarList(it)
                        }
                        FilteredListView()
                    }
                }
            }
        }
    }

    @Composable
    fun FilteredListView(){
        val filteredList = myViewModel.carDetailsFilteredList.observeAsState().value
        filteredList?.let { carList ->
            LazyColumn{
                items(carList){
                    CarItemView(carItem = it)
                }
            }
        }
    }

    @Composable
    fun CarItemView(carItem : Car){
    
        Column(Modifier.padding(16.dp)) {
            Row {
               Image(painter = painterResource(id = R.drawable.ic_bmw), contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
               Column {
                   Text(text = carItem.model)
                   Spacer(modifier = Modifier.width(10.dp))
                   Row { repeat(carItem.rating) { Icon(Icons.Default.Star, "", tint = Color(
                       0xB2FF9800
                   )
                   ) } }
               } 
                
            }
            
            
        }

    }

    @Composable
    fun ExpandedProsAndCons(carItem : Car){
        val cons = carItem.consList
        val pors = carItem.prosList
      Column {
           if(pors.isNotEmpty()){
              Text(text = "Pros:", style = TextStyle(fontWeight = FontWeight.Black, color = Color(0xFF464646)))
              Spacer(modifier = Modifier.height(20.dp))
              repeat(pors.size){
                  Row {
                      Box(
                          Modifier
                              .background(Color(0xB2FF9800))
                              .padding(end = 10.dp)){}
                      Text(text = pors[it])
                  }
              }

           }

          if(pors.isNotEmpty()){
              Text(text = "Cons:", style = TextStyle(fontWeight = FontWeight.Black, color = Color(0xFF464646)))
              Spacer(modifier = Modifier.height(20.dp))
              repeat(cons.size){
                  Row {
                      Box(
                          Modifier
                              .background(Color(0xB2FF9800))
                              .padding(end = 10.dp)){}
                      Text(text = cons[it])
                  }
              }
          }

        }

    }


}
