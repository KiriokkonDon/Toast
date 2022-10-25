package com.example.demo

import javafx.animation.Interpolator
import javafx.animation.TranslateTransition
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage


class HelloApplication : Application() {
    private var c1: Circle? = null
    private var c2: Circle? = null

    @Throws(Exception::class)
    override fun start(stage: Stage) {
        val pane = AnchorPane()

        // Set only the radius
        c1 = Circle(5.0)
        c1!!.fill = Color.BLUE

        // Let's translate the c1 to the location we want
        c1!!.translateX = 50.0
        c1!!.translateY = 60.0

        // The same for circle2
        c2 = Circle(5.0)
        c2!!.fill = Color.RED
        c2!!.translateX = 120.0
        c2!!.translateY = 200.0
        pane.children.addAll(c1, c2)
        pane.onMouseClicked = EventHandler { e: MouseEvent? -> startAnimation() }
        stage.scene = Scene(pane, 400.0, 500.0)
        stage.show()
    }

    private fun startAnimation() {
        val transition = TranslateTransition()
        transition.node = c1
        transition.toX = c2!!.translateX
        transition.toY = c2!!.translateY
        transition.interpolator = Interpolator.LINEAR
        transition.play()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(*args)
        }
    }
}