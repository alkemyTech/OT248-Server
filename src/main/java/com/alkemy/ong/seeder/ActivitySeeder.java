package com.alkemy.ong.seeder;

import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActivitySeeder {

    @Autowired
    private ActivityRepository activityRepository;

    long millisSeconds = System.currentTimeMillis();
    Date date = new Date(millisSeconds);

    @EventListener
    public void evenListener (ContextRefreshedEvent contextRefreshedEvent){
        if (activityRepository.count() < 10) createActivities();
    }

    public void createActivities() {
        Activity activity = new Activity();

        activity.setName("Ayuda escolar");
        activity.setContent("Brindamos ayuda escolar para chicos de primero a septimo grado en las " +
                "materias de Lengua, Matematica, Ciencias Naturales y Ciencias Sociales.");
        activity.setImage("https://static.misionesonline.news/wp-content/uploads/2019/02/ayuda-escolar-6hgorbp7lfj0.jpg");
        activity.setCreateAt(date);
        activity.setUpdateAt(date);
        activityRepository.save(activity);

        Activity activity2 = new Activity();
        activity2.setName("Ayuda escolar");
        activity2.setContent("Brindamos ayuda escolar para chicos de primero a septimo grado en las " +
                "materias de Lengua, Matematica, Ciencias Naturales y Ciencias Sociales.");
        activity2.setImage("https://static.misionesonline.news/wp-content/uploads/2019/02/ayuda-escolar-6hgorbp7lfj0.jpg");
        activity2.setCreateAt(date);
        activityRepository.save(activity2);

        Activity activity3 = new Activity();
        activity3.setName("Transformación social");
        activity3.setContent("Las ONG desarrollamos un papel esencial para informar " +
                "y sensibilizar a la sociedad sobre problemas que afectan al mundo " +
                "global como la pobreza, la desigualdad, la injusticia, las crisis " +
                "humanitarias, los desplazamientos de la población o el cambio climático.");
        activity3.setImage("https://blog.microwd.es/wp-content/uploads/2018/07/IC-SERES-EADA-Midiendo-el-impacto-social-del-a-RSE.png");
        activity3.setCreateAt(date);
        activity3.setUpdateAt(date);
        activityRepository.save(activity3);
        Activity activity4 = new Activity();
        activity4.setName("Impulsar proyectos");
        activity4.setContent("Gracias al apoyo de nuestros socios, socias, instituciones y" +
                "empresas colaboradoras, Ayuda en Acción ha transformado ya la vida de " +
                "millones de personas que hoy en día tienen una vida mejor.");
        activity4.setImage("https://d500.epimg.net/cincodias/imagenes/2015/07/10/financiacion/1436513107_552669_1436513180_noticia_normal.jpg");
        activity4.setCreateAt(date);
        activity4.setUpdateAt(date);
        activityRepository.save(activity4);
        Activity activity5 = new Activity();
        activity5.setName("Sensibilizar a la sociedad");
        activity5.setContent("Las ONG desarrollamos un papel esencial para informar y" +
                "sensibilizar a la sociedad sobre problemas que afectan al mundo global " +
                "como la pobreza, la desigualdad, la injusticia, las crisis humanitarias, " +
                "los desplazamientos de la población o el cambio climático.");
        activity5.setImage("https://www.lavanguardia.com/files/image_449_220/files/fp/uploads/2022/03/25/623de20f5e86a.r_d.2698-1906-821.jpeg");
        activity5.setCreateAt(date);
        activity5.setUpdateAt(date);
        activityRepository.save(activity5);
        Activity activity6 = new Activity();
        activity6.setName("Promover la participación");
        activity6.setContent("Este es el modelo de trabajo de Ayuda en Acción. Trabajamos " +
                "vinculando a las personas a los proyectos desde el principio para para " +
                "garantizar que les puedan dar continuidad una vez que nuestra organización " +
                "se retira del territorio.");
        activity6.setImage("https://aptus.com.ar/wp-content/uploads/2020/03/participacion-clase.jpg");
        activity6.setCreateAt(date);
        activity6.setUpdateAt(date);
        activityRepository.save(activity6);
        Activity activity7 = new Activity();
        activity7.setName("Fortalecer a la ciudadanía");
        activity7.setContent("Todas las personas tenemos derechos civiles, pero también sociales, " +
                "económicos y culturales. La labor de las ONG es valiosísima para que las personas " +
                "más vulnerables puedan acceder al conocimiento de sus derechos.");
        activity7.setImage("https://formacionciudadanajohn.files.wordpress.com/2015/07/colaborar4.jpg");
        activity7.setCreateAt(date);
        activity7.setUpdateAt(date);
        activityRepository.save(activity7);
        Activity activity8 = new Activity();
        activity8.setName("Complementar las políticas públicas");
        activity8.setContent("El cometido de las ONG no es reemplazar el trabajo y responsabilidades " +
                "de los Estados y Organismos Internacionales. Nuestras acciones complementan" +
                " la labor de la administración. Los gobiernos nos reconocen a las ONG como " +
                "actores clave en la lucha contra la pobreza.");
        activity8.setImage("https://tecreview.tec.mx/wp-content/uploads/2021/05/politicas-publicas-.jpg");
        activity8.setCreateAt(date);
        activity8.setUpdateAt(date);
        activityRepository.save(activity8);
        Activity activity9 = new Activity();
        activity9.setName("Trabajar con honestidad");
        activity9.setContent("Algunas personas desconfían de las ONG y si realmente llevan a cabo " +
                "una adecuada gestión de los fondos que gestionan. Las ONG dependemos de la confianza " +
                "de la sociedad para contar con su colaboración, por ello, es clave llevar a cabo una " +
                "gestión impecable de nuestro trabajo.");
        activity9.setImage("https://www.amitai.com/es/wp-content/uploads/2018/12/valores-honestidad-en-el-trabajo.jpg");
        activity9.setCreateAt(date);
        activity9.setUpdateAt(date);
        activityRepository.save(activity9);
        Activity activity10 = new Activity();
        activity10.setName("Trabajar con profesionalidad");
        activity10.setContent("Para Ayuda en Acción una de nuestras máximas es nuestra política de transparencia. " +
                "Periódicamente informamos a nuestros socios y socias de cómo empleamos los fondos que aportan, " +
                "aportando información sobre los avances de los proyectos a los que se destinan");
        activity10.setImage("https://static.educaweb.com/img/contenidos/190308_certificado-profesionalidad.jpg");
        activity10.setCreateAt(date);
        activity10.setUpdateAt(date);
        activityRepository.save(activity10);
        Activity activity11 = new Activity();
        activity11.setName("Trabajar con compromiso");
        activity11.setContent("Nuestra web, donde anualmente publicamos nuestra memoria, también es otro canal de " +
                "rendición de cuentas de nuestra actuación. Además, nos sometemos a diversas auditorías externas " +
                "que avalan que nuestra gestión es totalmente clara y transparente.");
        activity11.setImage("https://canopylab.com/wp-content/uploads/2021/07/20943459-scaled.jpg");
        activity11.setCreateAt(date);
        activity11.setUpdateAt(date);
        activityRepository.save(activity11);
    }

}
