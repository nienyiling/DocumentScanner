package com.example.documentscanner

sealed class ProcessingError : Exception() {
    data class OcrError(override val message: String) : ProcessingError()
    data class TranslationError(override val message: String) : ProcessingError()
    data class ExportError(override val message: String) : ProcessingError()
    data class NetworkError(override val message: String) : ProcessingError()
    data class StorageError(override val message: String) : ProcessingError()
}
