package com.example.starbattle.tutorial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.leom.commondatas.RulesSectionStrings
import com.leom.commondatas.SwipeTutorialStrings
import com.leom.commondatas.TapTutorialStrings
import com.leom.commondatas.TutorialScreenStrings
import com.leom.game_tutorial_ui.R
import com.leom.game_tutorial_ui.theme.BorderLines
import com.leom.game_tutorial_ui.theme.Borders
import com.leom.game_tutorial_ui.theme.StarBattleTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TutorialScreenComponentUI(
    tutorialStringArgs: TutorialScreenStrings,
    rulesSectionArgs: RulesSectionStrings,
    tapTutorialArgs: TapTutorialStrings,
    swipeTutorialArgs: SwipeTutorialStrings,
    isDarkMode: Boolean,
    onBackClick: () -> Unit,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val samplePuzzle = createSamplePuzzle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(StarBattleTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopAppBar
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tutorialStringArgs.tutorialTitle,
                            style = MaterialTheme.typography.bodyLarge,
                            color = StarBattleTheme.colors.textPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = StarBattleTheme.colors.textPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onThemeToggle) {
                        Icon(
                            painter = painterResource(
                                id = if (isDarkMode) R.drawable.point_light else R.drawable.moon_dark
                            ),
                            contentDescription = "Toggle theme",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 게임 소개
                TutorialSection(
                    title = tutorialStringArgs.appName,
                    iconRes = R.drawable.star,
                    backgroundColor = Color(0xFFFFEB8A),
                    isDarkMode = isDarkMode
                ) {
                    Text(
                        text = tutorialStringArgs.tutorialDescription,
                        style = MaterialTheme.typography.bodyLarge,
                        color = StarBattleTheme.colors.textSecondary
                    )
                }

                // 게임 규칙
                RulesSection(rulesSectionArgs = rulesSectionArgs, isDarkMode = isDarkMode, samplePuzzle = samplePuzzle)

                // 조작 방법
                TutorialSection(
                    title = tutorialStringArgs.controlsTitle,
                    iconRes = R.drawable.gesture_press,
                    backgroundColor = Color(0xFFEDD5FF),
                    isDarkMode = isDarkMode
                ) {
                    InteractiveTutorial(tapTutorialArgs = tapTutorialArgs, swipeTutorialArgs = swipeTutorialArgs)
                }

                // 게임 팁
                TutorialSection(
                    title = tutorialStringArgs.tipsTitle,
                    iconRes = R.drawable.light_bulb,
                    backgroundColor = Color(0xFFBFDEF5),
                    isDarkMode = isDarkMode
                ) {
                    Item(
                        number = "01",
                        text = tutorialStringArgs.tip1,
                    )
                    Item(
                        number = "02",
                        text = tutorialStringArgs.tip2,
                    )
                    Item(
                        number = "03",
                        text = tutorialStringArgs.tip3,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TutorialSection(
    title: String,
    iconRes: Int,
    backgroundColor: Color,
    isDarkMode: Boolean,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = backgroundColor.copy(alpha = if (isDarkMode) 0.6f else 0.9f),
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = backgroundColor.copy(alpha = if (isDarkMode) 0.1f else 0.2f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = StarBattleTheme.colors.textPrimary
            )
        }
        content()
    }
}

@Composable
private fun RulesSection(
    rulesSectionArgs: RulesSectionStrings,
    isDarkMode: Boolean,
    samplePuzzle: SamplePuzzle
) {
    var expandedRule by remember { mutableStateOf("rule1") }

    TutorialSection(
        title = rulesSectionArgs.rulesTitle,
        iconRes = R.drawable.pencil,
        backgroundColor = Color(0xFFFFC18A),
        isDarkMode = isDarkMode
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            // Rule 1
            RuleAccordionItem(
                title = rulesSectionArgs.ruleAdjacentTitle,
                isExpanded = expandedRule == "rule1",
                isFirst = true,
                onClick = { expandedRule = if (expandedRule == "rule1") "" else "rule1" }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = rulesSectionArgs.ruleAdjacentDesc,
                        style = MaterialTheme.typography.bodyLarge,
                        color = StarBattleTheme.colors.textSecondary
                    )
                    AdjacentRuleExample()
                    Text(
                        text = rulesSectionArgs.ruleAdjacentExample,
                        style = MaterialTheme.typography.bodySmall,
                        color = StarBattleTheme.colors.textSecondary
                    )
                }
            }
            RuleAccordionItem(
                title = rulesSectionArgs.ruleStarsTitle,
                isExpanded = expandedRule == "rule2",
                isFirst = true,
                onClick = { expandedRule = if (expandedRule == "rule2") "" else "rule2" }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = rulesSectionArgs.ruleStarsDesc,
                        style = MaterialTheme.typography.bodyLarge,
                        color = StarBattleTheme.colors.textSecondary
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = StarBattleTheme.colors.background,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Grid Size
                            Text(
                                text = "8×8",
                                style = MaterialTheme.typography.bodyMedium,
                                color = StarBattleTheme.colors.textSecondary
                            )

                            // Required Stars
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                repeat(2) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = rulesSectionArgs.ruleStarsExample,
                        style = MaterialTheme.typography.bodySmall,
                        color = StarBattleTheme.colors.textSecondary
                    )
                }
            }

            // Rule 2
            RuleAccordionItem(
                title = rulesSectionArgs.ruleRowColTitle,
                isExpanded = expandedRule == "rule3",
                onClick = { expandedRule = if (expandedRule == "rule3") "" else "rule3" }
            ) {
                Column {
                    Text(
                        text = rulesSectionArgs.ruleRowColDesc,
                        style = MaterialTheme.typography.bodyLarge,
                        color = StarBattleTheme.colors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PuzzleGridWithHighlight(
                        puzzle = samplePuzzle,
                        highlightRow = 2,
                        highlightCol = 2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = rulesSectionArgs.ruleRowColExample,
                        style = MaterialTheme.typography.bodySmall,
                        color = StarBattleTheme.colors.textSecondary
                    )
                }
            }

            // Rule 3
            RuleAccordionItem(
                title = rulesSectionArgs.ruleBlockTitle,
                isExpanded = expandedRule == "rule4",
                isLast = true,
                onClick = { expandedRule = if (expandedRule == "rule4") "" else "rule4" }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = rulesSectionArgs.ruleBlockDesc,
                        style = MaterialTheme.typography.bodyLarge,
                        color = StarBattleTheme.colors.textSecondary
                    )
                    PuzzleGridWithHighlight(
                        puzzle = samplePuzzle,
                        highlightRegion = "B"
                    )
                    Text(
                        text = rulesSectionArgs.ruleBlockExample,
                        style = MaterialTheme.typography.bodySmall,
                        color = StarBattleTheme.colors.textSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun RuleAccordionItem(
    title: String,
    isExpanded: Boolean,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        if (!isFirst) {
            Divider(
                color = StarBattleTheme.colors.textSecondary.copy(alpha = 0.1f),
                thickness = 1.dp
            )
        }

        Surface(
            onClick = onClick,
            color = if (isExpanded) {
                Color(0xFFFFAE67).copy(alpha = 0.3f)
            } else {
                Color.Transparent
            },
            shape = RoundedCornerShape(
                topStart = if (isFirst) 12.dp else 0.dp,
                topEnd = if (isFirst) 12.dp else 0.dp,
                bottomStart = if (isLast && !isExpanded) 12.dp else 0.dp,
                bottomEnd = if (isLast && !isExpanded) 12.dp else 0.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isExpanded) FontWeight.SemiBold else FontWeight.Medium
                    ),
                    color = StarBattleTheme.colors.textPrimary,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = if (isExpanded) "접기" else "펼치기",
                    tint = StarBattleTheme.colors.textPrimary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFAE67).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(
                            bottomStart = if (isLast) 12.dp else 0.dp,
                            bottomEnd = if (isLast) 12.dp else 0.dp
                        )
                    )
                    .clip(
                        RoundedCornerShape(
                            bottomStart = if (isLast) 12.dp else 0.dp,
                            bottomEnd = if (isLast) 12.dp else 0.dp
                        )
                    )
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun Item(
    number: String,
    text: String,
    color: Color = StarBattleTheme.colors.textSecondary
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = StarBattleTheme.colors.textPrimary,
            modifier = Modifier.padding(end = 12.dp, top = 2.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AdjacentRuleExample(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(160.dp)
                .aspectRatio(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(3) { row ->
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { col ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .border(
                                    width = 1.dp,
                                    color = StarBattleTheme.colors.textPrimary.copy(alpha = 0.8f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                row == 1 && col == 1 -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                else -> {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(
                                                color = StarBattleTheme.colors.textSecondary.copy(
                                                    alpha = 0.5f
                                                ),
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PuzzleGridWithHighlight(
    puzzle: SamplePuzzle,
    highlightRow: Int? = null,
    highlightCol: Int? = null,
    highlightRegion: String? = null,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Grid Size
                Text(
                    text = "${puzzle.gridSize}×${puzzle.gridSize}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = StarBattleTheme.colors.textSecondary
                )

                // Required Stars
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(puzzle.requiredStars) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(240.dp)
                    .aspectRatio(1f)
            ) {
                repeat(puzzle.gridSize) { row ->
                    Row(modifier = Modifier.weight(1f)) {
                        repeat(puzzle.gridSize) { col ->
                            val index = row * puzzle.gridSize + col
                            val region = puzzle.regions[index]
                            val hasStar = puzzle.answer[index] == 1

                            val isHighlighted = (highlightRow == row) ||
                                    (highlightCol == col) ||
                                    (highlightRegion != null && region == highlightRegion)

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(
                                        when {
                                            isHighlighted -> StarBattleTheme.colors.primary.copy(
                                                alpha = 0.2f
                                            )

                                            highlightRegion != null -> {
                                                // 블록 하이라이트 모드일 때 다른 블록들의 색상을 더 연하게 표시
                                                regionColors[region]?.copy(alpha = 0.1f)
                                                    ?: StarBattleTheme.colors.backgroundGradient
                                            }

                                            else -> {
                                                // 일반적인 경우 (행/열 하이라이트 또는 기본 표시)
                                                regionColors[region]?.copy(alpha = 0.3f)
                                                    ?: StarBattleTheme.colors.backgroundGradient
                                            }
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (hasStar) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                BorderLines(
                                    borders = getBorders(row, col, region, puzzle.regions, puzzle.gridSize),
                                    borderColor = StarBattleTheme.colors.textSecondary,
                                    thinLineColor = StarBattleTheme.colors.textSecondary.copy(alpha = 0.3f),
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// 경계선 계산 함수
private fun getBorders(
    row: Int,
    col: Int,
    region: String,
    regions: List<String>,
    gridSize: Int
): Borders {
    fun getRegion(r: Int, c: Int): String? {
        if (r < 0 || r >= gridSize || c < 0 || c >= gridSize) return null
        return regions[r * gridSize + c]
    }

    return Borders(
        top = region != getRegion(row - 1, col),
        right = region != getRegion(row, col + 1),
        bottom = region != getRegion(row + 1, col),
        left = region != getRegion(row, col - 1)
    )
}

@Composable
fun InteractiveTutorial(
    tapTutorialArgs: TapTutorialStrings,
    swipeTutorialArgs: SwipeTutorialStrings,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 탭 조작 튜토리얼
        TapTutorial(tapTutorialArgs = tapTutorialArgs)

        // 스와이프 조작 튜토리얼
        SwipeTutorial(swipeTutorialArgs = swipeTutorialArgs)
    }
}

@Composable
private fun TapTutorial(
    tapTutorialArgs: TapTutorialStrings
) {
    var cellState by remember { mutableIntStateOf(0) } // 0: 빈칸, 1: 점, 2: 별

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // 제목 텍스트
        Item(
            number = "01",
            text = tapTutorialArgs.controlTap,
            color = StarBattleTheme.colors.textPrimary
        )

        // 설명 텍스트
        Text(
            text = tapTutorialArgs.controlTapDesc,
            style = MaterialTheme.typography.bodyMedium,
            color = StarBattleTheme.colors.textSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 인터랙티브 셀

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = StarBattleTheme.colors.backgroundGradient,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = StarBattleTheme.colors.textSecondary.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    cellState = (cellState + 1) % 3
                },
            contentAlignment = Alignment.Center
        ) {
            when (cellState) {
                0 -> { /* 빈칸 */ }
                1 -> {
                    // 점
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = StarBattleTheme.colors.textSecondary.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                    )
                }
                2 -> {
                    // 별
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SwipeTutorial(
    swipeTutorialArgs: SwipeTutorialStrings
) {
    var swipedCells by remember { mutableStateOf(List(9) { false }) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // 제목 텍스트
        Item(
            number = "02",
            text = swipeTutorialArgs.controlSwipe,
            color = StarBattleTheme.colors.textPrimary
        )

        Text(
            text = swipeTutorialArgs.controlSwipeDesc,
            style = MaterialTheme.typography.bodyLarge,
            color = StarBattleTheme.colors.textSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 3x3 그리드
        val gridModifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    change.position.let { position ->
                        val row = (position.y / (size.height / 3)).toInt().coerceIn(0, 2)
                        val col = (position.x / (size.width / 3)).toInt().coerceIn(0, 2)
                        val index = row * 3 + col
                        if (index in swipedCells.indices) {
                            swipedCells = swipedCells.toMutableList().apply {
                                this[index] = true
                            }
                        }
                    }
                }
            }

        Box(
            modifier = Modifier
                .width(200.dp)
                .aspectRatio(1f)
        ) {
            Column(modifier = gridModifier.fillMaxSize()) {
                repeat(3) { row ->
                    Row(modifier = Modifier.weight(1f)) {
                        repeat(3) { col ->
                            val index = row * 3 + col
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .border(
                                        width = 1.dp,
                                        color = StarBattleTheme.colors.textSecondary.copy(alpha = 0.3f)
                                    )
                                    .background(
                                        color = StarBattleTheme.colors.backgroundGradient
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (swipedCells[index]) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(
                                                color = StarBattleTheme.colors.textSecondary.copy(
                                                    alpha = 0.5f
                                                ),
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 리셋 버튼
        TextButton(
            onClick = { swipedCells = List(9) { false } },
            colors = ButtonDefaults.textButtonColors(
                contentColor = StarBattleTheme.colors.textSecondary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(swipeTutorialArgs.retry)
        }
    }
}