package com.example.voiceup.domain.usecase

import javax.inject.Inject

data class IssuesUseCases @Inject constructor(
    val addUseCase: AddUseCase,
    val deleteUseCase: DeleteUseCase,
    val updateUseCase: UpdateUseCase,
    val getAllUseCase: GetAllUseCase
)
