package main.model.claim.property;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

// TODO Override toString

public class PropertyClaim extends Claim {

    private Type type;

    public enum Type
    {
        A(Loc.c("claim_property_a")),
        B(Loc.c("claim_property_b")),
        C(Loc.c("claim_property_c"));

        String value;

        Type(String value){ this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }

    }

    public static class Builder extends ClaimBuilder<Builder, PropertyClaim> {

        private Type type;

        public Builder(Person customer, Insurance insurance) {
            super(customer, insurance);
        }

        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }

        @Override
        public PropertyClaim build() {
            return new PropertyClaim(this);
        }
    }

    /**
     * Claim constructor.
     *
     * @param builder
     */
    public PropertyClaim(Builder builder) {
        super(builder);
        type = builder.type;
    }

    @Override
    public ClaimType identify() { return ClaimType.PROPERTY; }

    @Override
    public ModelType getModelType() {
        return ModelType.CLAIM;
    }
}