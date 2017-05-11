package by.test.photoapptest.di;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import by.test.photoapptest.di.module.PresenterModule;

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public static void buildAppComponent(Context context) {
        component = DaggerAppComponent.builder()
                .presenterModule(new PresenterModule(context))
                .build();
    }

    public static AppComponent getAppComponent() {
        return component;
    }
}