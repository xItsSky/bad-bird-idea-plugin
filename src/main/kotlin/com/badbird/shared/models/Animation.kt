package com.badbird.shared.models

import java.net.URL

private const val IMAGE_EXTENSION = "png"
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

    fun toNextImage() {
        val nextIndex = this.images.indexOf(this.currentImage) + 1
        this.currentImage = this.images[nextIndex]
    }

    enum class AnimationState {
        IDLE,
        PLAYING,
        PAUSED
    }

    object AnimationFactory : IAnimationsFactory {

        /**
         * Create the animation with the pooping bird
         */
        @JvmStatic val BAD_BIRD_ANIMATION = createAnimation("badbird", 60, "/assets/images/poop-animation")

        /**
         * Create an animation based on the list of ImagesNames and a folder
         *
         * @param baseName: the base of the image names
         * @param imagesNumbers: the number of images
         * @param folder: the folder of all images
         */
        override fun createAnimation(baseName: String, imagesNumbers: Int, folder: String) = Animation(getImagesInFolder(getImageNamesList(baseName, imagesNumbers), folder), IPF)

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