package com.badbird.components.windowtools

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiTreeChangeEvent
import com.intellij.psi.PsiTreeChangeListener
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class BirdWindowTools: ToolWindowFactory {


    private lateinit var imageLabel: JLabel
    private lateinit var panel: JPanel
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        panel = JPanel()
        imageLabel = JLabel()
        panel.add(imageLabel)
        toolWindow.contentManager.addContent(
                toolWindow.contentManager.factory.createContent(
                        panel, "", false
                )
        )

        val psiManager: PsiManager = PsiManager.getInstance(project)

        val psiTreeChangeListener = object : PsiTreeChangeListener {
            override fun beforeChildAddition(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun beforeChildRemoval(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun beforeChildReplacement(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun beforeChildMovement(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun beforeChildrenChange(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun beforePropertyChange(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun childAdded(event: PsiTreeChangeEvent) {
                if(event.element is PsiErrorElement) {
                    // TODO poop
                    //(event.element as PsiErrorElement)
                }
                updateImage(project, imageLabel)
            }

            override fun childRemoved(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun childReplaced(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun childrenChanged(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun childMoved(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

            override fun propertyChanged(event: PsiTreeChangeEvent) {
                updateImage(project, imageLabel)
            }

        }

        psiManager.addPsiTreeChangeListener(psiTreeChangeListener)

        updateImage(project, imageLabel)
    }

    private fun updateImage(project: Project, image: JLabel) {

        val urlImage = javaClass.getResource("/assets/images/high_success.jpg")
        val imageIcon = ImageIcon(urlImage)
        image.icon = imageIcon

        image.revalidate()
        image.repaint()

    }

}