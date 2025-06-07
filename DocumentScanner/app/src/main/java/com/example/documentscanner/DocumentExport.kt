package com.example.documentscanner

import java.io.File

enum class ExportFormat {
    WORD,
    PDF,
    PLAIN_TEXT
}

data class ExportOptions(
    val format: ExportFormat,
    val includeOriginal: Boolean = true,
    val includeTranslation: Boolean = false,
    val includeSummary: Boolean = false,
    val includeMetadata: Boolean = true,
    val pageNumbers: Boolean = true
)

interface DocumentExporter {
    suspend fun export(
        document: Document,
        options: ExportOptions
    ): ExportResult

    fun isFormatSupported(format: ExportFormat): Boolean
    fun getSupportedFormats(): List<ExportFormat>
}

data class ExportResult(
    val file: File,
    val format: ExportFormat,
    val size: Long,
    val exportTime: Long
)
