package com.badbird.components.windowtools

import com.badbird.components.listeners.BirdPsiTreeChangeListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiManager
import javax.swing.JLabel
import javax.swing.JPanel

class BirdWindowTools : ToolWindowFactory {
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
        birdListener.triggerImageUpdate("/assets/images/badbird0000.png")
    }
}