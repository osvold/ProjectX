package main.model.insurance.travel;

import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Travel extends Insurance implements Serializable
{

    /**
     * List of zones the insurance is covering.
     * Suggestions of zone:
     * Just the continents. 
     */
    private List<String> zones;
    private int sum;

    private Travel(Builder builder)
    {
        super(builder);
        zones = builder.zones;
        sum = builder.sum;
    }

    public ConcreteType identify()
    {
        return ConcreteType.TRAVEL;
    }

    public boolean query(String value)
    {
        return super.query(value);
    }

    public static class Builder extends InsuranceBuilder<Builder, Travel>
    {
        private List<String> zones = new ArrayList<>();
        private int sum;

        public Builder addZone(String zone)
        {
            return this;
        }

        public Builder sum(int sum)
        {

            return this;
        }

        public Builder(Person customer)
        {
            super.customer(customer);
        }

        public Travel build()
        {
            return new Travel(this);
        }
    }


    // TODO override equals and hashcode
}
