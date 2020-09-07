package com.agilityio.open_api_demo.factory.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.agilityio.open_api_demo.model.core.Department;
import com.agilityio.open_api_demo.model.core.Employee;
import com.agilityio.open_api_demo.model.core.Task;
import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoreTemplateLoader implements TemplateLoader {

    private static final String templateDefaultLabel = TemplateLabel.DEFAULT.name();

    @Override
    public void load() {
        Faker faker = new Faker();

        Fixture.of(Employee.class).addTemplate(templateDefaultLabel, new Rule(){{
            add("id", faker.internet().uuid());
            add("firstName", faker.name().firstName());
            add("lastName", faker.name().lastName());
            add("email", faker.internet().emailAddress());
            add("mobileNumber", faker.phoneNumber().phoneNumber());
            add("department", one(Department.class, templateDefaultLabel));
        }});

        Fixture.of(Department.class).addTemplate(templateDefaultLabel, new Rule(){{
            add("id", faker.internet().uuid());
            add("name", faker.company().name());
            add("level", faker.number().randomDigit());
            add("revenue", faker.number().randomDouble(2, 100, 500));
        }});

        Fixture.of(Task.class).addTemplate(templateDefaultLabel, new Rule(){{
            add("id", faker.internet().uuid());
            add("title", faker.lorem().sentence());
            add("content", faker.lorem().paragraph());
            add("taskStatus", TaskStatus.values()[faker.random().nextInt(TaskStatus.values().length)]);
            add("start", faker.date().past(10, TimeUnit.DAYS));
            add("end", faker.date().past(5, TimeUnit.DAYS));
            add("employee", one(Employee.class, templateDefaultLabel));
        }});
    }

    public static Employee getEmployee() {
        return Fixture.from(Employee.class).gimme(templateDefaultLabel);
    }

    public static List<Employee> getEmployees(int numberOfItems) {
        return Fixture.from(Employee.class).gimme(numberOfItems, templateDefaultLabel);
    }

    public static Department getDepartment() {
        return Fixture.from(Department.class).gimme(templateDefaultLabel);
    }

    public static List<Department> getDepartments(int numberOfItems) {
        return Fixture.from(Employee.class).gimme(numberOfItems, templateDefaultLabel);
    }

    public static Task getTask() {
        return Fixture.from(Task.class).gimme(templateDefaultLabel);
    }

    public static List<Task> getTasks(int numberOfItems) {
        return Fixture.from(Task.class).gimme(numberOfItems, templateDefaultLabel);
    }
}
