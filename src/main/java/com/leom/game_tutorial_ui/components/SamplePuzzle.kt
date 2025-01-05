package com.example.starbattle.tutorial

import androidx.compose.ui.graphics.Color

data class SamplePuzzle(
    val gridSize: Int,
    val requiredStars: Int,
    val regions: List<String>,
    val grid: List<Int>,
    val answer: List<Int>
)

fun createSamplePuzzle() = SamplePuzzle(
    gridSize = 5,
    requiredStars = 1,
    regions = listOf(
        "A", "A", "A", "B", "C",
        "E", "E", "B", "B", "C",
        "E", "E", "B", "B", "D",
        "E", "E", "D", "D", "D",
        "E", "E", "E", "D", "D"
    ),
    grid = List(25) { 0 }, // 빈 상태
    answer = listOf(
        0, 1, 0, 0, 0,
        0, 0, 0, 0, 1,
        0, 0, 1, 0, 0,
        1, 0, 0, 0, 0,
        0, 0, 0, 1, 0
    )
)

val regionColors = mapOf(
    "A" to Color(0xFFB5B5B5),  // 회색
    "B" to Color(0xFFA8C6A1),  // 연두색
    "C" to Color(0xFFA1BAD6),  // 하늘색
    "D" to Color(0xFFD6A1A1),  // 연분홍
    "E" to Color(0xFFD6CCA1),  // 베이지
)