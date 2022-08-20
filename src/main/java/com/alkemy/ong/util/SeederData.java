package com.alkemy.ong.util;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeederData {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findAll().isEmpty()) createRole();
        if (userRepository.findAll().isEmpty()) createUsers();
        if(categoryRepository.count() < 3) createCategory();
        if(newsRepository.count() < 100) createNews(100 - newsRepository.count());
    }

    public void createRole() {
        String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
        for (int i = 0; i < roles.length; i++) {
            roleRepository.save(
                    Role.builder()
                            .name(roles[i])
                            .description("")
                            .createAt(new Date())
                            .build());
        }
    }

    public void createUsers() {
        String encrypt = passwordEncoder.encode("1234");
        List<Role> roles = roleRepository.findAll();
        List<Users> usuarios = new ArrayList<>();
        usuarios.add(new Users("juan", "perez",
                "juan@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("pepe", "jults",
                "pepe@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("pedro", "lopez",
                "pedro@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("ernesto", "ford",
                "ernesto@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("john", "sanchez",
                "john@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("samuel", "perez",
                "samuel@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("ivan", "andredde",
                "ivan@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("jose", "martinez",
                "jose@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("oscar", "bravo",
                "oscar@mail.com", encrypt, "", roles.get(0)));
        usuarios.add(new Users("estavan", "volarde",
                "estavan@mail.com", encrypt, "", roles.get(0)));


        usuarios.add(new Users("peet", "landino",
                "peet@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("mario", "acosta",
                "mario@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("bruno", "piñero",
                "bruno@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("rick", "goya",
                "rick@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("mary", "rincon",
                "mary@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("pepa", "avila",
                "pepa@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("vilma", "nuñes",
                "vilma@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("eddie", "garcia",
                "eddie@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("zully", "lubo",
                "zully@mail.com", encrypt, "", roles.get(1)));
        usuarios.add(new Users("briget", "lisboa",
                "briget@mail.com", encrypt, "", roles.get(1)));

        userRepository.saveAll(usuarios);
    }

    List<String> newsName = List.of("The Largest Investor of our NGO", "Why choose us?", "We are happy",
            "Incredible things happens", "A big new", "Come here and look this", "How did we make this possible?",
            "No beginning, no end", "To a new world", "A miracle",
            "Incredible things happens", "A big new");
    List<String> newsContent = List.of("\"Our country has lost four of its very best citizens. We families lost loved ones forever,\" she said....",
            "In April, the 12-person jury deliberated for less than six hours over two days before finding Elsheikh guilty...",
            "Witnesses recounted receiving random beatings, being given dog names and being forced to sing a parody of the Eagles' pop song \"Hotel California\" retitled \"Hotel Osama\"...",
            "The three members - Elsheikh, Alexanda Kotey and Mohammed Emwazi - who are said to be part of...","\"Our country has lost four of its very best citizens. We families lost loved ones forever,\" she said....",
            "In April, the 12-person jury deliberated for less than six hours over two days before finding Elsheikh guilty...",
            "Witnesses recounted receiving random beatings, being given dog names and being forced to sing a parody of the Eagles' pop song \"Hotel California\" retitled \"Hotel Osama\"...",
            "The three members - Elsheikh, Alexanda Kotey and Mohammed Emwazi - who are said to be part of...","\"Our country has lost four of its very best citizens. We families lost loved ones forever,\" she said....",
            "In April, the 12-person jury deliberated for less than six hours over two days before finding Elsheikh guilty...",
            "Witnesses recounted receiving random beatings, being given dog names and being forced to sing a parody of the Eagles' pop song \"Hotel California\" retitled \"Hotel Osama\"...",
            "The three members - Elsheikh, Alexanda Kotey and Mohammed Emwazi - who are said to be part of...");
    List<String> newsImage = List.of("images.com/investor.jpg", "images.com/us.jpg", "images.com/things.jpg", "images.com/incredible.jpg",
            "images.com/investor.jpg", "images.com/us.jpg", "images.com/things.jpg", "images.com/incredible.jpg",
            "images.com/investor.jpg", "images.com/us.jpg", "images.com/things.jpg", "images.com/incredible.jpg");

    public void createNews (Long timesToCreate){
        Random random = new Random();
        List<Category> categories = List.of(categoryRepository.findById(1L).get(), categoryRepository.findById(2L).get(), categoryRepository.findById(1L).get());
        List<News> news = new ArrayList<>();

        for (int i = 0; i < timesToCreate; i++){
            int categoryNumber = random.nextInt(3);
            int randomNumber = random.nextInt(newsName.size());
            news.add(new News(newsName.get(randomNumber), newsContent.get(randomNumber), newsImage.get(randomNumber), categories.get(categoryNumber), new Date()));
        }
        newsRepository.saveAll(news);
    }
    public void createCategory (){
        Category donations = new Category("Donations", "Donations", "cloud.ko/help.jpg", new Date());
        Category today = new Category("Today", "Daily news", "cloud.ko/today.jpg", new Date());
        Category members = new Category("Us", "How we work", "cloud.ko/work.jpg", new Date());

        categoryRepository.saveAll(List.of(donations, today, members));
    }

}
