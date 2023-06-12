package com.badbird.components.windowtools

import com.badbird.components.listeners.BirdPsiTreeChangeListener
import com.badbird.shared.models.Animation
import com.badbird.shared.scheduler.AnimationScheduler
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiManager
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class BirdWindowTools : ToolWindowFactory {
    private val animation = Animation.AnimationFactory.BAD_BIRD_ANIMATION

    private val scheduler = AnimationScheduler(animation, 3)
    private var imageLabel = JLabel()
    private var panel = JPanel()


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        // Set up the panel
        panel.add(imageLabel)
        toolWindow.contentManager.addContent(toolWindow.contentManager.factory.createContent(panel, "", false))

        // Set up the listener
        val birdListener = BirdPsiTreeChangeListener(imageLabel)
        val psiManager = PsiManager.getInstance(project)
        psiManager.addPsiTreeChangeListener(birdListener)

        // Set default image
        this.setAnimationPanel()
    }

    private fun setAnimationPanel() {
        val urlImage = animation.currentImage
        val imageIcon = ImageIcon(urlImage)
        this.imageLabel.icon = imageIcon
        this.imageLabel.revalidate()
        this.imageLabel.repaint()
    }
}