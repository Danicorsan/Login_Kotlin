package com.example.sendmessagefragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class    AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.ic_launcher_background)
                .setCover(R.mipmap.ic_launcher_round)
                .setName("Daniel Cortes Sanchez")
                .setSubTitle("Mobile Developer")
                .setBrief("Esto es una practica de fragmentos.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("danicorsan")
                .addFacebookLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        setContentView(view);
    }
}