package main.view.concrete.insurance;

import javafx.scene.control.Label;
import main.localization.Loc;
import main.model.insurance.travel.Travel;

/**
 * TravelView.java
 */
public class TravelView extends InsuranceView<Travel>
{
    /**
     * Instantiates a new Travel view.
     *
     * @param travel the travel
     */
    public TravelView(Travel travel)
    {
        super(travel);
    }

    protected void childDraw()
    {
        // continent
        add(new Label(Loc.c("continent")), 0, rowNum);
        add(new Label(getInsurance().getType() + ""), 1, rowNum++);

        addClaimsTable();
    }
}
