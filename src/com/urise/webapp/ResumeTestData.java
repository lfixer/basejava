package com.urise.webapp;

import com.urise.webapp.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResumeTestData {

    public static void main(String[] args) {
    }

    public static Resume writeResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");

        resume.setSection(SectionType.PERSONAL, new SingleLineSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSection(SectionType.OBJECTIVE, new SingleLineSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        ArrayList<String> achievement = new ArrayList<>();
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setSection(SectionType.ACHIEVEMENT, new BulletedLineSection(achievement));

        ArrayList<String> qualification = new ArrayList<>();
        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy.");
        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        resume.setSection(SectionType.QUALIFICATIONS, new BulletedLineSection(qualification));

        ArrayList<Experience> experience = new ArrayList<>();
        try {
            experience.add(new Experience("Java Online Projects", new URL("https://javaops.ru/"), new Experience.Case("Автор проекта.", LocalDate.of(2013, 10, 1), LocalDate.now(), "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        resume.setSection(SectionType.EXPERIENCE, new Organisation(experience));

        ArrayList<Experience> education = new ArrayList<>();
        try {
            education.add(new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", new URL("https://itmo.ru/ru/"), new Experience.Case("", LocalDate.of(1993, 9, 1), LocalDate.of(1976, 7, 1), "Аспирантура (программист С, С++)"),
                    new Experience.Case("", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        resume.setSection(SectionType.EDUCATION, new Organisation(education));
        return resume;
    }

}
