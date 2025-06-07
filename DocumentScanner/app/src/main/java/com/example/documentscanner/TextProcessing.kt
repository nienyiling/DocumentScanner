package com.example.documentscanner

// Text processing result
data class TextProcessingResult(
    val originalText: String,
    val processedText: String,
    val processingType: ProcessingType,
    val confidence: Float,
    val processingTime: Long
)

enum class ProcessingType {
    SUMMARY,
    TRANSLATION
}

interface TextProcessor {
    suspend fun processText(
        text: String,
        type: ProcessingType,
        options: ProcessingOptions
    ): TextProcessingResult

    fun isAvailable(): Boolean
    fun getSupportedLanguages(): List<String>
}

data class ProcessingOptions(
    val targetLanguage: String? = null,
    val summaryLength: Int? = null,
    val style: ProcessingStyle = ProcessingStyle.FORMAL
)

enum class ProcessingStyle {
    FORMAL,
    CASUAL,
    ACADEMIC
}
