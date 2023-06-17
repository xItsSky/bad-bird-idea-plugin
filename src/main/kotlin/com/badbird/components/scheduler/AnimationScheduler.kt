package com.badbird.components.scheduler

import com.badbird.shared.models.Animation
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JLabel

/**
 * The delay to way before starting the scheduler
 */
private const val STARTING_DELAY = 3000L

/**
 * The Animation scheduler which manage an Animation
 *
 * @author ItsSky
 */
class AnimationScheduler(private val imageContainer: JLabel, private val animation: Animation, duration: Int) {
    private val timer = Timer()
    private val timerPeriod = (duration / animation.ips).toLong()

    /**
     * Run the scheduled task
     */
    fun run() {
        val task = AnimationSchedulerTask(imageContainer, animation)
        timer.schedule(task, STARTING_DELAY, this.timerPeriod)
    }

    /**
     * Cancel the scheduled task
     */
    fun cancel() {
        this.timer.cancel()
    }

    /**
     * Represent an Animation scheduler task which is in charge of updating the image container icon based on the animation
     *
     * @author ItsSky
     */
    class AnimationSchedulerTask(private val imageContainer: JLabel, private val animation: Animation) : TimerTask() {

        init {
            this.animation.playAnimation()

            // Initialize the image container icon
            this.setImageContainerIcon(imageContainer, ImageIcon(this.animation.currentImage))
        }

        /**
         * The run method of the task which update the image container icon based on the animation
         */
        override fun run() {
            // Updating the image container icon based on the animation
            this.setImageContainerIcon(this.imageContainer, ImageIcon(this.animation.animate()))

            if (this.animation.isPlayingOrPaused()) {
                // Updating the image container icon based on the animation
                this.setImageContainerIcon(this.imageContainer, ImageIcon(this.animation.animate()))
            } else {
                // Cancel the task if the animation doesn't run anymore
                this.cancel()
            }
        }

        /**
         * Sets the image container
         * @param imageContainer: the image container on which set the icon
         * @param icon: the icon to set
         */
        private fun setImageContainerIcon(imageContainer: JLabel, icon: ImageIcon) {
            imageContainer.icon = icon
            imageContainer.revalidate()
            imageContainer.repaint()
        }
    }
}