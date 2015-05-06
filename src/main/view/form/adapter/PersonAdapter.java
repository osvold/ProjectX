package main.view.form.adapter;

import main.config.Config;
import main.localization.Loc;
import main.model.Status;
import main.model.person.Person;
import main.validator.StringMatcher;
import main.view.form.node.FormChoiceNode;
import main.view.form.node.FormDateNode;
import main.view.form.node.FormNode;
import main.view.form.node.FormValueNode;
import main.view.form.Formable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 4/21/15.
 */
public class PersonAdapter implements Formable<Person>
{
    private FormValueNode firstname;
    private FormValueNode lastname;
    private FormDateNode dob;
    private FormValueNode city;
    private FormValueNode streetAddress;
    private FormValueNode postalCode;
    private FormValueNode phoneNumber;
    private FormValueNode email;
    private FormChoiceNode status;

    private Person person;

    // TODO rethink this implementation decision.
    private boolean editMode = false;

    public PersonAdapter(Person person)
    {
        if (person != null) {
            this.person = person;
            editMode = true;
        }
        initNodes();
    }

    public PersonAdapter() { this(null); }

    private void initNodes()
    {
        firstname = new FormValueNode.Builder(Loc.c("firstname"))
                .value(editMode ? person.getFirstname() : "")
                .error(Loc.c("firstname_error"))
                .regex(StringMatcher.getFirstname())
                .build();

        lastname = new FormValueNode.Builder(Loc.c("lastname"))
                .value(editMode ? person.getLastname() : "")
                .error(Loc.c("lastname_error"))
                .regex(StringMatcher.getLastname())
                .build();

        dob = new FormDateNode.Builder(Loc.c("date_of_birth"),
                editMode ? person.getDateOfBirth() : LocalDate.of(Config.STANDARD_YEAR,
                                                                  Config.STANDARD_MONTH,
                                                                  Config.STANDARD_DAY))
                .required(false)
                .build();

        city = new FormValueNode.Builder(Loc.c("city"))
                .value(editMode ? person.getCity() : "")
                .error(Loc.c("city_error"))
                .regex(StringMatcher.getBaseString())
                .required(false)
                .build();

        streetAddress = new FormValueNode.Builder(Loc.c("street_address"))
                .value(editMode ? person.getStreetAddress() : "")
                .error(Loc.c("street_address_error"))
                .regex(StringMatcher.getLiberation())
                .required(false)
                .build();

        postalCode = new FormValueNode.Builder(Loc.c("postal_code"))
                .value(editMode ? person.getPostalCode() : "")
                .error(Loc.c("postal_code_error"))
                .regex(StringMatcher.getDigit())
                .required(false)
                .build();

        phoneNumber = new FormValueNode.Builder(Loc.c("phone_number"))
                .value(editMode ? person.getPhoneNumber() : "")
                .error(Loc.c("phone_number_error"))
                .regex(Loc.c(StringMatcher.getDigit()))
                .required(false)
                .build();

        email = new FormValueNode.Builder(Loc.c("email"))
                .value(editMode ? person.getEmail() : "")
                .error(Loc.c("email_error"))
                .regex(Loc.c(StringMatcher.getEmail()))
                .required(false)
                .build();

        List<Enum> statusList = new ArrayList<>();
        for (Status s : Status.values()) { statusList.add(s); }

        status = new FormChoiceNode.Builder<>(Loc.c("status"), statusList)
                .active(editMode ? person.getStatus() : Status.ACTIVE)
                .build();
    }

    @Override
    public List<FormNode> getNodes()
    {
        List<FormNode> tmp = new ArrayList<>();
        tmp.add(firstname);
        tmp.add(lastname);
        tmp.add(dob);
        tmp.add(streetAddress);
        tmp.add(postalCode);
        tmp.add(city);
        tmp.add(phoneNumber);
        tmp.add(email);
        tmp.add(status);

        return tmp;
    }

    public void callback()
    {

        if (editMode) {
            person.setFirstname(firstname.getValue());
            person.setLastname(lastname.getValue());
            person.setDateOfBirth(dob.getData());
            person.setPostalCode(postalCode.getValue());
            person.setStreetAddress(streetAddress.getValue());
            person.setCity(city.getValue());
            person.setStatus((Status) status.getData()); // tsk tsk
            person.setEmail(email.getData());
            person.setPhoneNumber(phoneNumber.getData());
        } else {
            person = new Person.Builder(firstname.getValue(), lastname.getValue())
                    .streetAddress(streetAddress.getValue())
                    .dateOfBirth(dob.getData())
                    .postalCode(postalCode.getValue())
                    .status((Status)status.getData()) // tsk tsk
                    .city(city.getValue())
                    .email(email.getData())
                    .phoneNumber(phoneNumber.getData())
                    .build();
        }
        callBackEvent.fire();
    }

    @Override
    public void setOnDoneAction(Consumer<Person> c)
    {
        callBackEvent.setOnAction(e -> c.accept(person));
    }
}
