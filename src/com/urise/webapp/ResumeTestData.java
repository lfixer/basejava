package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");

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

        ArrayList<Organization> experience = new ArrayList<>();
        experience.add(new Organization("Java Online Projects", "Автор проекта.", LocalDate.of(2013, 10, 1), LocalDate.now(), "Создание, организация и проведение Java онлайн проектов и стажировок."));
        resume.setExperience(SectionType.EXPERIENCE, experience);

        ArrayList<Organization> education = new ArrayList<>();
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "" , LocalDate.of(1993, 9, 1), LocalDate.of(1976, 7, 1), "Аспирантура (программист С, С++)"));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)"));
        resume.setExperience(SectionType.EDUCATION, education);

        System.out.println("Имя: " + resume.getFullName() + "\nid: " + resume.getUuid());
        System.out.println(resume.getContacts());
        System.out.println(resume.getAllData());
    }
}
