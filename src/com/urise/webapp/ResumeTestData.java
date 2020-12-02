package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.util.ArrayList;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact("Phone", "+7(921) 855-0482");
        resume.setContact("Skype", "grigory.kislin");
        resume.setContact("Mail", "gkislin@yandex.ru");
        resume.setContact("LinkedIn", "https://www.linkedin.com/in/gkislin");
        resume.setContact("GitHub", "https://github.com/gkislin");

        resume.setData(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setData(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        ArrayList<String> achievement = new ArrayList<>();
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setData(SectionType.ACHIEVEMENT, achievement);

        ArrayList<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce.");
        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy.");
        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        resume.setData(SectionType.QUALIFICATIONS, qualification);

        ArrayList<String> experience = new ArrayList<>();
        experience.add("Java Online Projects\n" +
                "10/2013 - Сейчас\tАвтор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experience.add("Wrike\n" +
                "10/2014 - 01/2016\tСтарший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        experience.add("RIT Center\n" +
                "04/2012 - 10/2014\tJava архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        resume.setData(SectionType.EXPERIENCE, experience);

        System.out.println("Имя: " + resume.getFullName() + "\nid: " + resume.getUuid());
        System.out.println(resume.getContacts());
        System.out.println(resume.getAllData());
    }
}
