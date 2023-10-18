package org.example;


import jakarta.servlet.http.HttpSession;
import org.casbin.jcasbin.main.Enforcer;
import org.example.task.TaskEntity;
import org.example.task.TaskRepos;
import org.example.task.core.BaseJpaRepositoryFactoryBean;
import org.example.task.core.CreateEmployee;
import org.example.task.core.IQuerySpec;
import org.example.task.core.RegisteredQuerySpec;
import org.example.task.core.casbin.ModelRegistry;
import org.example.task.policy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.example"}, repositoryFactoryBeanClass = BaseJpaRepositoryFactoryBean.class)
public class AuthApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Autowired
    TaskRepos taskRepos;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public RegisteredQuerySpec registeredQuerySpec(){
        RegisteredQuerySpec r = new RegisteredQuerySpec();
        IQuerySpec spec = (IQuerySpec)applicationContext.getBean("querySpec");
        r.register(spec);
        return r;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new AuthInterceptor())
               .addPathPatterns("/**");
    }

    @PostConstruct
    public void init(){

        ModelRegistry modelRegistry = new ModelRegistry();


        String[] employeeName = {"alice","bob","lisi","bob"};
        for(int i=0,j=0;i<20;i++,j++){
            TaskEntity t = TaskEntity.builder()
                       .name("nn"+i)
                       .number(i)
                       .startTime(Date.from(Instant.now()))
                       .endTime(Date.from(Instant.now()))
                    .build();
            j = j > 3 ? 0 : j;
            t.addCreateEmployee(t.getId(),employeeName[j],employeeName[j]);

            taskRepos.save(t);
        }
    }

    @Bean
    public Enforcer enforcer(){
        PolicyRepo policyRepo = new PolicyRepo();
        List<Policy> p = new ArrayList<>();
       p.add( new Policy("alice","/task", Act.GET));
       p.add( new Policy("bob","/task", Act.POST));
        p.add( new Policy("lisi","/task", Act.DELETE));
        p.add( new Policy("bob","/task", Act.PUT));
        policyRepo.addPolicyList(p);
        PolicyAdapter policyAdapter = new PolicyAdapter();


        Enforcer enforcer = new Enforcer("examples/basic_model.conf",policyAdapter );
        return enforcer;
    }

//    private Faker faker = Faker.instance(Locale.CHINA);
//    @PostConstruct
//    public void init(){
//        for(int i=1 ; i< 50 ; i++) {
//
//            TaskEntity task = TaskEntity.builder()
//                    .id((long)i )
//                    .name(createData_Name())
//                    .number(i)
//                    .startTime(createData_Date())
//                    .endTime(createData_Date())
//                    .build();
//            TaskEntity taskSave = taskRepos.save(task);
//            System.out.println(taskSave);
//        }
//        aa();
//
//    }
//
//    private String createData_Name(){
//        return faker.name().name();
//    }
//    private Date createData_Date(){
//        return faker.date().birthday();
//    }
//
//    private void aa(){
//        Enforcer enforcer = new Enforcer("examples/basic_model.conf", "examples/basic_policy.csv");
//        String sub = "alice";
//        String obj = "data1";
//        String act = "read";
//
//        if (enforcer.enforce(sub, obj, act)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//    }
}
