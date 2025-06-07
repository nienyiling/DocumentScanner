package com.example.documentscanner

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class SimpleDocumentExporter(private val context: Context) : DocumentExporter {
    override fun isFormatSupported(format: ExportFormat): Boolean {
        return format == ExportFormat.WORD || format == ExportFormat.PLAIN_TEXT
    }

    override fun getSupportedFormats(): List<ExportFormat> {
        return listOf(ExportFormat.WORD, ExportFormat.PLAIN_TEXT)
    }

    override suspend fun export(
        document: Document,
        options: ExportOptions
    ): ExportResult = withContext(Dispatchers.IO) {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: context.filesDir
        val extension = if (options.format == ExportFormat.WORD) "doc" else "txt"
        val file = File(dir, "${document.title}.$extension")
        val builder = StringBuilder()
        builder.appendLine(document.originalText)
        if (options.includeTranslation && document.translatedText != null) {
            builder.appendLine(document.translatedText)
        }
        if (options.includeSummary && document.summary != null) {
            builder.appendLine(document.summary)
        }
        file.writeText(builder.toString())
        ExportResult(file, options.format, file.length(), System.currentTimeMillis())
    }
}
