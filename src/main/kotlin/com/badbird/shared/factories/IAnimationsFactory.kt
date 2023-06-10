package com.badbird.shared.factories

import com.badbird.shared.models.Animation

interface IAnimationsFactory {

    /**
     * Create a new Animation
     *
     * @param resourcesPath: the path containing all the images to use for the animation
     */
    fun createAnimation(resourcesPath: String): Animation
}