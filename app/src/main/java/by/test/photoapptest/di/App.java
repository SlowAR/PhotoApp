package by.test.photoapptest.di;

import android.app.Application;
import android.content.Context;

import by.test.photoapptest.di.module.PresenterModule;

public class App extends Application {
    private static AppComponent component;

    public static void buildAppComponent(Context context) {
        component = DaggerAppComponent.builder()
                .presenterModule(new PresenterModule(context))
                .build();
    }

    public static AppComponent getAppComponent() {
        return component;
    }
}