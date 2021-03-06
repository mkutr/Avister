package com.avister.utilities

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.avister.ml.models.MnistClassifier
import com.avister.navigation.MainMenuActivity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class ConfigurationManagerTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainMenuActivity::class.java)

    @Test
    fun loadConfigurationTest() {
        activityTestRule.scenario.onActivity {
            val configurationManager = ConfigurationManager(it)
            assertEquals("Avister", configurationManager["app_name"])
        }

    }

    @Test
    fun MusicDirectoryTest() {
        activityTestRule.scenario.onActivity {
            val configurationManager = ConfigurationManager(it)
            val musicDir = configurationManager["mainMusicDir"]
            assert(musicDir[0] != "")
        }

    }
    @Test
    fun ModelFileNameTest() {
        activityTestRule.scenario.onActivity {
            val configurationManager = ConfigurationManager(it)
            assert(configurationManager["modelFileName"][0] != "")
        }

    }
}

