/*package com.alkemy.ong.seeder;

import com.alkemy.ong.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class ActivityRunner implements CommandLineRunner {

    @Autowired
    private ActivityService activityService;

    @Override
    public void run(String... args) throws Exception {
        long millisSeconds = System.currentTimeMillis();
        Date date = new Date(millisSeconds);
        Method[] methods = Activity.class.getMethods();
        Activity activity = Activity.class.newInstance();

        for (Method method: methods) {
            if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                Object[] params = new Object[]{"test"};
                method.invoke(activity, params);

                activity.setName("Ayuda escolar");
                activity.setContent("Brindamos ayuda escolar para chicos de primero a septimo grado en las " +
                        "materias de Lengua, Matematica, Ciencias Naturales y Ciencias Sociales.");
                activity.setImage("https://static.misionesonline.news/wp-content/uploads/2019/02/ayuda-escolar-6hgorbp7lfj0.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Transformación social");
                activity.setContent("Las ONG desarrollamos un papel esencial para informar " +
                        "y sensibilizar a la sociedad sobre problemas que afectan al mundo " +
                        "global como la pobreza, la desigualdad, la injusticia, las crisis " +
                        "humanitarias, los desplazamientos de la población o el cambio climático.");
                activity.setImage("https://blog.microwd.es/wp-content/uploads/2018/07/IC-SERES-EADA-Midiendo-el-impacto-social-del-a-RSE.png");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Impulsar proyectos");
                activity.setContent("Gracias al apoyo de nuestros socios, socias, instituciones y" +
                        "empresas colaboradoras, Ayuda en Acción ha transformado ya la vida de " +
                        "millones de personas que hoy en día tienen una vida mejor.");
                activity.setImage("https://d500.epimg.net/cincodias/imagenes/2015/07/10/financiacion/1436513107_552669_1436513180_noticia_normal.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Sensibilizar a la sociedad");
                activity.setContent("Las ONG desarrollamos un papel esencial para informar y" +
                        "sensibilizar a la sociedad sobre problemas que afectan al mundo global " +
                        "como la pobreza, la desigualdad, la injusticia, las crisis humanitarias, " +
                        "los desplazamientos de la población o el cambio climático.");
                activity.setImage("https://www.lavanguardia.com/files/image_449_220/files/fp/uploads/2022/03/25/623de20f5e86a.r_d.2698-1906-821.jpeg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Promover la participación");
                activity.setContent("Este es el modelo de trabajo de Ayuda en Acción. Trabajamos " +
                        "vinculando a las personas a los proyectos desde el principio para para " +
                        "garantizar que les puedan dar continuidad una vez que nuestra organización " +
                        "se retira del territorio.");
                activity.setImage("https://aptus.com.ar/wp-content/uploads/2020/03/participacion-clase.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Fortalecer a la ciudadanía");
                activity.setContent("Todas las personas tenemos derechos civiles, pero también sociales, " +
                        "económicos y culturales. La labor de las ONG es valiosísima para que las personas " +
                        "más vulnerables puedan acceder al conocimiento de sus derechos.");
                activity.setImage("https://formacionciudadanajohn.files.wordpress.com/2015/07/colaborar4.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Complementar las políticas públicas");
                activity.setContent("El cometido de las ONG no es reemplazar el trabajo y responsabilidades " +
                        "de los Estados y Organismos Internacionales. Nuestras acciones complementan" +
                        " la labor de la administración. Los gobiernos nos reconocen a las ONG como " +
                        "actores clave en la lucha contra la pobreza.");
                activity.setImage("https://tecreview.tec.mx/wp-content/uploads/2021/05/politicas-publicas-.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Trabajar con honestidad");
                activity.setContent("Algunas personas desconfían de las ONG y si realmente llevan a cabo " +
                        "una adecuada gestión de los fondos que gestionan. Las ONG dependemos de la confianza " +
                        "de la sociedad para contar con su colaboración, por ello, es clave llevar a cabo una " +
                        "gestión impecable de nuestro trabajo.");
                activity.setImage("https://www.amitai.com/es/wp-content/uploads/2018/12/valores-honestidad-en-el-trabajo.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Trabajar con profesionalidad");
                activity.setContent("Para Ayuda en Acción una de nuestras máximas es nuestra política de transparencia. " +
                        "Periódicamente informamos a nuestros socios y socias de cómo empleamos los fondos que aportan, " +
                        "aportando información sobre los avances de los proyectos a los que se destinan");
                activity.setImage("https://static.educaweb.com/img/contenidos/190308_certificado-profesionalidad.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activity.setName("Trabajar con compromiso");
                activity.setContent("Nuestra web, donde anualmente publicamos nuestra memoria, también es otro canal de " +
                        "rendición de cuentas de nuestra actuación. Además, nos sometemos a diversas auditorías externas " +
                        "que avalan que nuestra gestión es totalmente clara y transparente.");
                activity.setImage("https://canopylab.com/wp-content/uploads/2021/07/20943459-scaled.jpg");
                activity.setCreateAt(date);
                activity.setUpdateAt(date);
                activity.setDeleted(false);

                activityService.saveActivity(activity);
            }
        }
    }
}*/
