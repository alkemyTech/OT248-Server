package com.alkemy.ong.seeder;

import com.alkemy.ong.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class ActivityRunner implements CommandLineRunner {

    @Autowired
    private ActivityService activityService;

    @Override
    public void run(String... args) throws Exception {
        long millisSeconds = System.currentTimeMillis();
        Date date = new Date(millisSeconds);

        Activity activity = new Activity();

        activity.setName("Jonathan");
        activity.setContent("Contenido");
        activity.setImage("http://www.image.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Turco");
        activity.setContent("Contenido2");
        activity.setImage("http://www.image2.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Emmir");
        activity.setContent("Contenido3");
        activity.setImage("http://www.image3.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Andres");
        activity.setContent("Contenido4");
        activity.setImage("http://www.image4.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Juan Ceron");
        activity.setContent("Contenido5");
        activity.setImage("http://www.image5.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Juan Manuel");
        activity.setContent("Contenido6");
        activity.setImage("http://www.image6.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Juana");
        activity.setContent("Contenido7");
        activity.setImage("http://www.image7.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Niki");
        activity.setContent("Contenido8");
        activity.setImage("http://www.image8.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Marco");
        activity.setContent("Contenido9");
        activity.setImage("http://www.image9.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activity.setName("Lambruschini");
        activity.setContent("Contenido10");
        activity.setImage("http://www.image10.com");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activity.setDeleted(false);

        activityService.saveActivity(activity);
    }
}
