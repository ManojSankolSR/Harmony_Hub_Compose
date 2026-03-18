package com.example.harmonyhub.features.storage.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.storage.data.models.TotalStorageInfo
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Pie

@Composable
fun StorageOverviewCard(info: TotalStorageInfo) {
    var chartData = remember(info) {
        listOf(
            Pie(
                label = "App Data",
                data = info.appUsedStorage.toDouble(),
                color = Color(0xFF6200EE),
                selectedColor = Color(0xFF3700B3)
            ),
            Pie(
                label = "Other Used",
                data = (info.deviceUsedStorage - info.appUsedStorage).coerceAtLeast(0L).toDouble(),
                color = Color(0xFFFFB74D),
                selectedColor = Color(0xFFF57C00)
            ),
            Pie(
                label = "Free Space",
                data = info.deviceAvailableStorage.toDouble(),
                color = Color(0xFF81C784),
                selectedColor = Color(0xFF388E3C)
            )
        )
    }


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {

            PieChart(
                data = chartData,
                style = Pie.Style.Stroke(width = 50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                spaceDegree = 2f,
                labelHelperProperties = LabelHelperProperties(enabled = false),
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Custom centered labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                chartData.forEach { pie ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(pie.color)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = pie.label ?: "",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }}
