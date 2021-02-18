package com.tomsk.android.beatbox

import org.hamcrest.CoreMatchers.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {


    private lateinit var sound: Sound
    private lateinit var testedUnit: SoundViewModel
    private lateinit var beatBox: BeatBox

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)
        sound=Sound("assetPath")
        testedUnit = SoundViewModel(beatBox)
        testedUnit.sound=sound
    }

    @Test
    fun  exposesSoundNameAsTitle() {
        assertThat(testedUnit.title, `is`(sound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        testedUnit.onButtonClicked()

        verify(beatBox).play(sound)
    }

}