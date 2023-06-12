package com.badbird.shared.scheduler

import com.badbird.shared.models.Animation
import java.util.*

class AnimationScheduler(animation: Animation, duration: Int) {
    private val timer = Timer()

    init {
        timer.schedule(AnimationSchedulerTask(animation), 0L, (animation.ips * duration).toLong())
    }

    class AnimationSchedulerTask(private val animation: Animation) : TimerTask() {

        override fun run() {
            if (Animation.AnimationState.PLAYING == animation.state) {
                this.animation.toNextImage()
            }
        }
    }
}