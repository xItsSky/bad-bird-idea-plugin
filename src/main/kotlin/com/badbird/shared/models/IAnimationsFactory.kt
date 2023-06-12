package com.badbird.shared.models

interface IAnimationsFactory {

    /**
     * Create a new Animation
     *
     * @param baseName:  the base name of all images to use in the animation
     * @param imagesNumbers: the number of image to use in the animation
     * @param folder: the path containing all the images to use for the animation
     */
    fun createAnimation(baseName: String, imagesNumbers: Int, folder: String): Animation
}