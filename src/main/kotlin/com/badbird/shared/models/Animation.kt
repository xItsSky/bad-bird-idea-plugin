package com.badbird.shared.models

import java.net.URL

private const val IPF = 30

/**
 * An object representing an animation
 *
 * @author ItsSky
 */
class Animation(images: List<URL?>, ips: Int) {

    /**
     * All the images in the animation
     */
    val images: List<URL?> = images
        get() = field

    var currentImage = images[0]
        get() = field

    /**
     * the image per frame
     */
    val ips = ips
        get() = field


    var state: AnimationState = AnimationState.IDLE
        get() = field

    /**
     * Manage the animation by playing it or not accordingly to the state
     */
    fun animate(): URL? {
        println("[Animate] state: $state, currentImage: ${images.indexOf(currentImage)}")

        if (this.state == AnimationState.PLAYING) {
            // If the animation is playing
            if (this.images.indexOf(this.currentImage) != (this.images.size - 1)) {
                // If the animation isn't ended, change the current image to the next one
                val nextIndex = this.images.indexOf(this.currentImage) + 1
                this.currentImage = this.images[nextIndex]
            } else {
                // If the animation is ended, resetting the animation
                this.resetAnimation()
            }
        }

        return this.currentImage;
    }

    /**
     * Reset the Animation by setting the current image to the first one and set the state to IDLE
     */
    fun resetAnimation() {
        this.state = AnimationState.IDLE
        this.currentImage = this.images.first()!!
    }

    /**
     * change the animation state to put the animation in playing mode
     */
    fun playAnimation() {
        this.state = AnimationState.PLAYING;
    }

    /**
     * Change the animation state to put the animation in pause mode
     */
    fun pauseAnimation() {
        this.state = AnimationState.PAUSED;
    }

    /**
     * @return whether the animation is currently paused or playing
     */
    fun isPlayingOrPaused() = this.state == AnimationState.PLAYING || this.state == AnimationState.PAUSED

    /**
     * The Animation State
     */
    enum class AnimationState {
        IDLE,
        PLAYING,
        PAUSED
    }

    /**
     * The factory to use to create an Animation
     */
    object AnimationFactory : IAnimationsFactory {

        /**
         * Create the animation with the pooping bird
         */
        @JvmStatic
        val BAD_BIRD_ANIMATION = createAnimation("badbird", 60, "/assets/images/poop-animation")

        /**
         * Create an animation based on the list of ImagesNames and a folder
         *
         * @param baseName: the base of the image names
         * @param imagesNumbers: the number of images
         * @param folder: the folder of all images
         */
        override fun createAnimation(baseName: String, imagesNumbers: Int, folder: String) =
            Animation(getImagesInFolder(getImageNamesList(baseName, imagesNumbers), folder), IPF)

        /**
         * Generate the list of images names
         *
         * @param baseName: the base of the image names
         * @param number: the number of images
         */
        private fun getImageNamesList(baseName: String, number: Int): List<String> {
            val names = ArrayList<String>()
            for (i in 0 until number + 1) {
                names.add(baseName + "%04d".format(i) + ".png")
            }
            return names
        }

        /**
         * Get Images from the folder and generate a list of url
         *
         * @param imageNames: the list of names of all images
         * @param folder: the folder of all images
         */
        private fun getImagesInFolder(imageNames: List<String>, folder: String): List<URL?> {
            return imageNames.map { name -> javaClass.getResource("$folder/$name") }.toList()
        }
    }
}