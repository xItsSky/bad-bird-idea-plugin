package com.badbird.components.windowtools

import com.badbird.components.listeners.BirdPsiTreeChangeListener
import com.badbird.shared.models.Animation
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiManager
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class BirdWindowTools : ToolWindowFactory {
    private val animation = Animation.AnimationFactory.createAnimation("src/main/resources/assets/images/poop-animation")

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
        val image = animation.currentImage
        val urlImage = javaClass.getResource(image.path + image.name)
        val imageIcon = ImageIcon(urlImage)
        this.imageLabel.icon = imageIcon
        this.imageLabel.revalidate()
        this.imageLabel.repaint()
    }
}