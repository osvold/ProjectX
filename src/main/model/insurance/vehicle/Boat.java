package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Boat extends Vehicle implements Serializable
{
    private int registrationYear;
    private int length;
    private int horsePower;
    private Propulsion propulsion;
    private Type type;
    private Person owner;

    public enum Propulsion {
        A(Loc.get("boat_propulsion_a")),
        B(Loc.get("boat_propulsion_b")),
        C(Loc.get("boat_propulsion_c"));

        String value;

        Propulsion(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public enum Type {
        A(Loc.get("boat_type_a")),
        B(Loc.get("boat_type_b")),
        C(Loc.get("boat_type_c")),
        D(Loc.get("boat_type_d"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public static class Builder extends InsuranceBuilder<Builder, Boat>
    {
        private String regNr;

        private Person owner = getCustomer();
        private int registrationYear = 1990;
        private int length = 0;
        private int horsePower = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;
        private int amount;
        private int premium;
        private String desc;

        public Builder(Person customer, String regNr)
        {
            super.customer(customer);
            this.regNr = regNr;
        }

        public Builder amount(int val)
        {
            amount = val;
            return this;
        }

        public Builder premium(int val)
        {
            premium = val;
            return this;
        }

        public Builder desc(String val)
        {
            desc = val;
            return this;
        }

        public Builder registrationYear(int val)
        {
            registrationYear = val;
            return this;
        }

        public Builder length(int val)
        {
            length = val;
            return this;
        }

        public Builder horsePower(int val)
        {
            horsePower = val;
            return this;
        }

        public Builder type(Type val)
        {
            type = val;
            return this;
        }

        public Builder propulsion(Propulsion val)
        {
            propulsion = val;
            return this;
        }

        public Builder owner(Person val)
        {
            owner = val;
            return this;
        }

        public Boat build()
        {
            return new Boat(this);
        }
    }


    private Boat(Builder builder)
    {
        super(builder, builder.owner, builder.regNr);

        registrationYear = builder.registrationYear;
        length = builder.length;
        horsePower = builder.horsePower;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    public int getRegistrationYear()
    {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear)
    {
        this.registrationYear = registrationYear;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public int getHorsePower()
    {
        return horsePower;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }

    public ConcreteType identify()
    {
        return ConcreteType.BOAT;
    }

    public void setPropulsion(Propulsion propulsion)
    {
        this.propulsion = propulsion;
    }

    public void setHorsePower(int horsePower)
    {
        this.horsePower = horsePower;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().contains(value))
                || (propulsion != null && propulsion.getValue().contains(value));
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
