package com.example.demo

import javafx.animation.FadeTransition
import javafx.animation.TranslateTransition
import javafx.application.Application
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.paint.Color
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration
import java.io.File

enum class ImageStyle {

    CIRCLE, RECTANGLE
}



enum class Position{
    LEFT_BOTTOM,
    LEFT_TOP,
    RIGHT_BOTTOM,
    RIGHT_TOP

}

class Config {
    var alpha = 0.9
    var openTime = 11000.0
    var imageType = ImageStyle.RECTANGLE
    var title = "TITLE"
    var message = "MESSAGE"
    var appName = "APP NAME"
    var image = "https://sun9-14.userapi.com/OmWPhIb7r1GBgFMJ5SrwlAP89OBQrwdcMNbBww/RSphMj3MChA.jpg"
    var position = com.example.demo.Position.RIGHT_BOTTOM
}

class Toast {
    private var config = Config()
    private val windows = Stage()
    private var root = BorderPane()
    private var box = HBox()

    class Builder {
        private var config = Config()

        fun setTitle(str: String): Builder {
            config.title = str
            return this
        }

        fun setMessage(str: String): Builder {
            config.message = str
            return this
        }

        fun setAppName(str: String): Builder {
            config.appName = str
            return this
        }

            fun build(): Toast  {
            var toast = Toast()
            toast.config = config
            toast.build()

            return toast
        }


    }


    private fun build() {

        var btn = Button("Close")
        btn.style = "-fx-background-color: Grey ; -fx-font-weight:bold;"
        btn.onAction = EventHandler {
            windows.close()
        }
        root.children.add(btn)

        windows.initStyle(StageStyle.TRANSPARENT)


        windows.scene = Scene(root,Color.TRANSPARENT)

        root.style = "-fx-background-color: Black"
        root.padding = Insets(10.0, 10.0, 10.0, 10.0)

        setImage()

        val vbox = VBox()

        val title = Label(config.title)
        val message = Label(config.message)
        val appName = Label(config.appName)



        title.style="-fx-background-color: linear-gradient(to right bottom, #FBF2EB, #352A3B);\n" +
                "-fx-text-fill: black;\n" +
                "-fx-font-weight:bold;"
        message.style="-fx-background-color: linear-gradient(to right bottom,  #352A3B, #FBF2EB);\n" +
                "-fx-text-fill: black;\n" +
                "-fx-font-weight:bold;"
        appName.style="-fx-background-color: linear-gradient(to right bottom, #FBF2EB, #352A3B);\n" +
                "-fx-text-fill: black;\n" +
                "-fx-font-weight:bold;"



        vbox.children.addAll(title, message, appName,btn)
        box.children.add(vbox)
        root.center = box


    }

    private fun setImage() {
        if (config.image.isEmpty()) {
            return
        }

        val iconBorder = if (config.imageType == ImageStyle.RECTANGLE) {
            Rectangle(70.0, 70.0)
        }
        else {
            Circle(50.0, 50.0, 50.0)
        }
        iconBorder.setFill(ImagePattern(Image(config.image)))
        box.children.add(iconBorder)
    }

    private fun openAnimation() {
        val alt_anim = TranslateTransition(Duration.millis(20000.0), root)
        if (config.position == com.example.demo.Position.RIGHT_TOP){
            windows.x = 765.0 + windows.width
            windows.y=0.0
            alt_anim.fromY = -100.0
            alt_anim.toY = 50.0
            }
        else if(config.position == com.example.demo.Position.LEFT_TOP){
            windows.x = 00.0
            windows.y=00.0
            alt_anim.fromY = -100.0
            alt_anim.toY = 50.0
        }
        if(config.position == com.example.demo.Position.RIGHT_BOTTOM){
            windows.x= 710.0 + windows.width
            windows.y=660.0 + windows.height
            alt_anim.fromY = 100.0
            alt_anim.toY = -50.0
        }
        else if(config.position == com.example.demo.Position.LEFT_BOTTOM){
            windows.x= 00.0
            windows.y=765.0
            alt_anim.fromY = 100.0
            alt_anim.toY = -50.0
        }
        alt_anim.play()
    }
    private fun closeAnimation() {
        val anim = FadeTransition(Duration.millis(1500.0), root)
        anim.fromValue = config.alpha
        anim.toValue = 0.0
        anim.cycleCount = 1
        anim.onFinished = EventHandler {
            Platform.exit()
            System.exit(0)
        }
        anim.play()
    }

    fun start() {
        windows.show()
        openAnimation();
        val thread = Thread {
            try {
                Thread.sleep(config.openTime.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            closeAnimation()
        }
        Thread(thread).start()

    }


}




fun music(){
    val musicFile = "C:\\CanYouFeel.mp3"
    val sound = Media(File(musicFile).toURI().toString())
    val mediaPlayer = MediaPlayer(sound)
    mediaPlayer.play()
}

class SomeClass: Application() {
    override fun start(p0: Stage?) {
        music()
        var toast = Toast.Builder()
            .setTitle("Каждый в цирке думает, что знает в цирке,")
            .setMessage("но не каждый, что в цирке знает, ")
            .setAppName("что в цирке не каждый знает думает.")
            .build()
        toast.start()
        }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(SomeClass::class.java)
        }
    }
}