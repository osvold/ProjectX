package model.insurance.vehicle;

import localization.Loc;
import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Boat extends Vehicle
{
    private final int registrationYear;
    private final int length;
    private final int horsePower;
    private final Propulsion propulsion;
    private final Type type;
    private Person owner;

    public enum Propulsion {
        A(Loc.get("boat_propulsion_a")),
        B(Loc.get("boat_propulsion_b")),
        C(Loc.get("boat_propulsion_c"));

        String value;

        Propulsion(String value)
        {
            this.value = value;
        }

        public String getValue() { return value; }
    }

    public enum Type {
        A(Loc.get("boat_type_a")),
        B(Loc.get("boat_type_b")),
        C(Loc.get("boat_type_c")),
        D(Loc.get("boat_type_d"));

        String value;

        Type(String value)
        {
            this.value = value;
        }

        public String getValue() { return value; }
    }

    public static class Builder
    {
        private String regNr;
        private Person customer;

        private Person owner            = customer;
        private int registrationYear    = 1900;
        private int length              = 0;
        private int horsePower          = 0;
        private String desc             = "";
        private Calendar date           = null;
        private double amount           = 0;
        private double premium          = 0;
        private Propulsion propulsion   = Propulsion.A;
        private Type type               = Type.A;

        public Builder(Person customer, String regNr)
        {
            this.regNr = regNr;
            this.customer = customer;
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

        public Boat build()
        {
            return new Boat(this);
        }

        public Builder desc(String val)
        {
            desc = val;
            return this;
        }

        public Builder date(Calendar val)
        {
            date = val;
            return this;
        }

        public Builder premium(double val)
        {
            premium = val;
            return this;
        }

        public Builder amount(double val)
        {
            amount = val;
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
    }


    public Boat(Builder b)
    {
        super(b.customer, b.premium, b.amount, b.date, b.desc, b.owner, b.regNr);
        registrationYear     = b.registrationYear;
        length               = b.length;
        horsePower           = b.horsePower;
        type                 = b.type;
        propulsion           = b.propulsion;
    }

    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }

    public String toString(){
        return super.toString();
    }


}

