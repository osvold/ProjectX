package main.model.insurance.property;

import main.localization.Loc;
import main.model.claim.property.PropertyClaim;
import main.model.insurance.InsuranceType;
import main.model.insurance.InsuranceBuilder;
import main.model.person.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * VacationHouse.java
 */
public class VacationHouse extends Property implements Serializable {

    private Type type;

    public enum Type {
        A(Loc.c("vacation_house_type_a")),
        B(Loc.c("vacation_house_type_b")),
        C(Loc.c("vacation_house_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public static class Builder extends InsuranceBuilder<Builder, VacationHouse>
    {
        private String streetAddress;
        private String postalCode;
        private String city = Loc.u("N/A");
        private int year = 1950;
        private int squareMeter = 0;
        private int contents = 0;
        private Type type = Type.A;
        private Material material = Material.A;
        private Standard standard = Standard.A;

        public Builder(Person customer,
                       String streetAddress,
                       String postalCode)
        {
            super.customer(customer);
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
            this.squareMeter = squareMeter;
            return this;
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

        @Override
        public List<PropertyClaim> getClaimsList()
        {
            return new LinkedList<>();
        }

        @Override
        public VacationHouse build() {
            return new VacationHouse(this);
        }
    }

    public VacationHouse(Builder builder) {
        super(builder);

        setStreetAddress(builder.streetAddress);
        setPostalCode(builder.postalCode);
        setMaterial(builder.material);
        setSquareMeter(builder.squareMeter);
        setStandard(builder.standard);
        setCity(builder.city);
        setYear(builder.year);
        setContents(builder.contents);

        type = builder.type;
    }

    /* SETTERS */
    public void setType(VacationHouse.Type type)
    {
        this.type = type;
    }

    /* GETTERS */
    public VacationHouse.Type getType()
    {
        return type;
    }

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (getStreetAddress() != null && getStreetAddress().toLowerCase().contains(value.toLowerCase()))
                || (getPostalCode() != null && getPostalCode().toLowerCase().contains(value.toLowerCase()))
                || (getCity() != null && getCity().toLowerCase().contains(value.toLowerCase()))
                || (getType() != null && getType().getValue().toLowerCase().contains(value.toLowerCase()));
    }

    @Override
    public InsuranceType identify() {
        return InsuranceType.VACATION_HOUSE;
    }
}
