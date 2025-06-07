package com.example.documentscanner

import android.graphics.Bitmap
import android.graphics.Rect

// OCR processing result
data class OcrResult(
    val text: String,
    val confidence: Float,
    val language: String,
    val blocks: List<TextBlock>,
    val processingTime: Long
)

data class TextBlock(
    val text: String,
    val confidence: Float,
    val boundingBox: Rect,
    val language: String
)

interface OcrProcessor {
    suspend fun processImage(image: Bitmap): OcrResult
    fun isAvailable(): Boolean
    fun getSupportedLanguages(): List<String>
    fun isLanguageSupported(language: String): Boolean
}

interface OcrProcessorFactory {
    fun createProcessor(type: OcrType): OcrProcessor
}

enum class OcrType {
    CLOUD_VISION,
    ML_KIT
}
