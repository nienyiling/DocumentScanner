package com.example.documentscanner

// Document data model
data class Document(
    val id: String,
    val title: String,
    val originalText: String,
    val translatedText: String? = null,
    val summary: String? = null,
    val scanDate: Long,
    val pages: List<Page>,
    val metadata: DocumentMetadata,
    val processingConfig: ProcessingConfig
)

data class Page(
    val pageNumber: Int,
    val originalText: String,
    val translatedText: String? = null,
    val confidence: Float,
    val imagePath: String? = null
)

data class DocumentMetadata(
    val author: String? = null,
    val creationDate: Long? = null,
    val lastModified: Long = System.currentTimeMillis(),
    val tags: List<String> = emptyList(),
    val language: String = "zh-TW"
)

data class ProcessingConfig(
    val useCloudOcr: Boolean = true,
    val useCloudNlp: Boolean = true,
    val exportFormat: ExportFormat = ExportFormat.WORD,
    val translationEnabled: Boolean = false,
    val summaryEnabled: Boolean = false
)
