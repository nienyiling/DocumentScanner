package com.example.documentscanner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun ScanScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isScanning by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var format by remember { mutableStateOf(ExportFormat.WORD) }

    val exporter = remember(context) { SimpleDocumentExporter(context) }

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = format == ExportFormat.WORD, onClick = { format = ExportFormat.WORD })
            Text(text = "Word")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(selected = format == ExportFormat.PLAIN_TEXT, onClick = { format = ExportFormat.PLAIN_TEXT })
            Text(text = "Txt")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            isScanning = true
            message = "Scanning started"
        }, enabled = !isScanning) {
            Text(text = "開始掃描")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            isScanning = false
            scope.launch {
                val doc = Document(
                    id = UUID.randomUUID().toString(),
                    title = "scan_${System.currentTimeMillis()}",
                    originalText = "Sample scanned text",
                    scanDate = System.currentTimeMillis(),
                    pages = emptyList(),
                    metadata = DocumentMetadata(),
                    processingConfig = ProcessingConfig()
                )
                val options = ExportOptions(format)
                val result = exporter.export(doc, options)
                message = "Saved: ${result.file.absolutePath}"
            }
        }, enabled = isScanning) {
            Text(text = "結束掃描")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (message.isNotEmpty()) {
            Text(text = message)
        }
    }
}
