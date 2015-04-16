package model.insurance.property;

import localization.Loc;
import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class House extends Property
{
    private String streetAddress;
    private String postalCode;
    private String city;
    private int year;
    private int squareMeter;

    private Type type;
    private Material material;
    private Standard standard;

    public enum Type {
        A(Loc.get("house_type_a")),
        B(Loc.get("house_type_b")),
        C(Loc.get("house_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

    }
    public enum Material {
        A(Loc.get("house_material_a")),
        B(Loc.get("house_material_b")),
        C(Loc.get("house_material_c"));

        String value;

        Material(String value) { this.value = value; }

        public String getValue() { return value; }
    }
    public enum Standard {
        A(Loc.get("house_standard_a")),
        B(Loc.get("house_standard_b")),
        C(Loc.get("house_standard_c"));

        String value;

        Standard(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public static class Builder
    {
        private Person customer;
        private String streetAddress;
        private String postalCode;
        private String city = "";
        private double amount = 0;
        private double premium = 0;
        private Calendar date = null;
        private String desc = "";
        private int year = 2000;
        private int squareMeter = 100;
        private Type type = Type.A;
        private Material material = Material.A;
        private Standard standard = Standard.A;

        public Builder(Person customer,
                       String streetAddress,
                       String postalCode)
        {
            this.customer = customer;
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
        }

        public Builder city(String city)
        {
            this.city = city; return this;
        }

        public Builder year(int year)
        {
            this.year = year; return this;
        }

        public Builder squareMeter(int squareMeter)
        {
            this.squareMeter = squareMeter; return this;
        }

        public Builder type(Type type)
        {
            this.type = type; return this;
        }

        public Builder material(Material material)
        {
            this.material = material; return this;
        }

        public Builder standard(Standard standard)
        {
            this.standard = standard; return this;
        }

        public Builder amount(double amount)
        {
            this.amount = amount; return this;
        }

        public Builder premium(double premium)
        {
            this.premium = premium; return this;
        }

        public Builder date(Calendar date)
        {
            this.date = date; return this;
        }

        public Builder desc(String desc)
        {
            this.desc = desc; return this;
        }

        public House build()
        {
            return new House(this);
        }
    }

    private House(Builder builder)
    {
        super(builder.customer,
              builder.premium,
              builder.amount,
              builder.date,
              builder.desc);

        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;
        year = builder.year;
        squareMeter = builder.squareMeter;
        type = builder.type;
        material = builder.material;
        standard = builder.standard;

    }
}
