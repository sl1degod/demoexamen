package ru.sl1degod.demoexamen.models;

import javafx.scene.Scene;

public class SceneModel {
    public static Scene myScene;

    public static Scene getMyScene() {
        return myScene;
    }

    public static void setMyScene(Scene myScene) {
        SceneModel.myScene = myScene;
    }
}
