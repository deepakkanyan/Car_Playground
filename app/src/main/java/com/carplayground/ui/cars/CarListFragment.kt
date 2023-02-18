package com.carplayground.ui.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.carplayground.ui.component.BannerView
import com.carplayground.ui.component.BoxWithDropdowns

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarListFragment : Fragment() {

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InitializeView() {
        Scaffold{
            LazyColumn(Modifier.padding(it)){
                item {
                    BannerView()
                }

                item {
                    BoxWithDropdowns()
                }


            }
        }
    }

}
