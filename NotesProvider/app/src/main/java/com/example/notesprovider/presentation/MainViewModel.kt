package com.example.notesprovider.presentation

import androidx.lifecycle.ViewModel
import com.example.notesprovider.domain.use_case.DeleteUseCase
import com.example.notesprovider.domain.use_case.GetAllNotesUseCase
import com.example.notesprovider.domain.use_case.InsertUseCase
import com.example.notesprovider.domain.use_case.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {


}