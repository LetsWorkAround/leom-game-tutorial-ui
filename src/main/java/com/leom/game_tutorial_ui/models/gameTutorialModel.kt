package com.leom.game_tutorial_ui.models

public data class TutorialScreenStrings(
    val tutorialTitle: String,
    val appName: String,
    val tutorialDescription: String,
    val controlsTitle: String,
    val tipsTitle: String,
    val tip1: String,
    val tip2: String,
    val tip3: String,
)

public data class TapTutorialStrings(
    val controlTap: String,
    val controlTapDesc: String,
)


public data class RulesSectionStrings(
    val rulesTitle: String,
    val ruleAdjacentTitle: String,
    val ruleAdjacentDesc: String,
    val ruleAdjacentExample: String,
    val ruleStarsTitle: String,
    val ruleStarsDesc: String,
    val ruleStarsExample: String,
    val ruleRowColTitle: String,
    val ruleRowColDesc: String,
    val ruleRowColExample: String,
    val ruleBlockTitle: String,
    val ruleBlockDesc: String,
    val ruleBlockExample: String,
)

public data class SwipeTutorialStrings(
    val controlSwipe: String,
    val controlSwipeDesc: String,
    val retry: String
)