package com.badbird.shared.models

import com.badbird.shared.factories.IAnimationsFactory

private const val IMAGE_EXTENSION = "png"
private const val IPF = 30

/**
 * An object representing an animation
 *
 * @author ItsSky
 */
class Animation(images: List<Image>, ipf: Int) {

    /**
     * All the images in the animation
     */
    val images: List<Image> = images
        get() = field

    var currentImage = images[0]
    get() = field

    /**
     * the image per frame
     */
    val ipf = ipf
        get() = field


    var state: AnimationState = AnimationState.IDLE
        get() = field
    
    enum class AnimationState {
        IDLE,
        PLAYING,
        PAUSED
    }

    object AnimationFactory : IAnimationsFactory {

        override fun createAnimation(resourcesPath: String) = Animation(getImagesInFolder(resourcesPath), IPF)

        private fun getImagesInFolder(resourcesPath: String): List<Image> {
            return this.javaClass.classLoader.resources(resourcesPath).map { Image(it.path, it.file) }.toList()
            /**val path = Paths.get(Paths.get("").toAbsolutePath().toString(), resourcesPath)
            return Files.walk(path)
                .filter { item -> Files.isRegularFile(item) && item.toString().endsWith(IMAGE_EXTENSION)}
                .map { item -> Image(resourcesPath, item.name) }
                .toList()**/
        }
    }
}