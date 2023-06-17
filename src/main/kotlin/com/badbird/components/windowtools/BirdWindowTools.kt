package com.badbird.components.windowtools

import com.badbird.components.listeners.BirdPsiTreeChangeListener
import com.badbird.components.scheduler.AnimationScheduler
import com.badbird.shared.models.Animation
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiManager
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * The duration of the bird animation
 */
private const val ANIMATION_DURATION = 3000

/**
 * The Bird Window Tools which manage the Bird window
 *
 * @author ItsSky
 */
class BirdWindowTools : ToolWindowFactory {
    /**
     * The BadBird animation
     */
    private val animation = Animation.AnimationFactory.BAD_BIRD_ANIMATION


    /**
     * Graphical components
     */
    private var imageLabel = JLabel()
    private var panel = JPanel()

    /**
     * The animation scheduler
     */
    private lateinit var scheduler: AnimationScheduler

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        // Set up the panel
        panel.add(imageLabel)
        toolWindow.contentManager.addContent(toolWindow.contentManager.factory.createContent(panel, "", false))

        // Set up the listener
        val birdListener = BirdPsiTreeChangeListener(imageLabel)
        val psiManager = PsiManager.getInstance(project)
        psiManager.addPsiTreeChangeListener(birdListener) // TODO: Seems to be deprecated. Need to be rewrite

        // Initialize the animation scheduler
        this.scheduler = AnimationScheduler(imageLabel, animation, ANIMATION_DURATION)
        this.scheduler.run()
    }
}