package main.view.concrete.insurance;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import main.controller.ClaimController;
import main.controller.InsuranceController;
import main.controller.TableController;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.insurance.Insurance;
import main.view.StandardGridPane;

/**
 * Created by alex on 4/28/15.
 */
public abstract class InsuranceView<T extends Insurance> extends StandardGridPane
{
    private T insurance;
    protected int rowNum = 0;

    public InsuranceView(T insurance)
    {
        this.insurance = insurance;

        initButtonPanel();
        initInsuranceFields();
    }

    public void initButtonPanel()
    {
        ToolBar buttonPane = new ToolBar();

        Button b1 = new Button(Loc.c("claim"));
        switch (insurance.identify()) {
            case CAR:
                b1.setOnAction(e -> ClaimController.create(Claim.ClaimType.CAR,
                        insurance.getCustomer(), insurance));
                break;
            case BOAT:
                //b1.setOnAction(e -> ClaimController.create(insurance.getCustomer(), insurance));
                break;
            case HOUSE:
                //b1.setOnAction(e -> ClaimController.create(insurance.getCustomer(), insurance));
                break;
            case VACATION_HOUSE:
                //b1.setOnAction(e -> ClaimController.create(insurance.getCustomer(), insurance));
                break;
            case TRAVEL:
                //b1.setOnAction(e -> ClaimController.create(insurance.getCustomer(), insurance));
                break;

        }

        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> InsuranceController.edit(insurance));

        buttonPane.getItems().addAll(b1, editButton);

        add(buttonPane, 0, rowNum++, 2, 1);
    }


    public void initInsuranceFields()
    {
        add(new Label(Loc.c("customer")), 0, rowNum);
        add(new Label(insurance.getCustomer().getName()), 1, rowNum++);

        add(new Label(Loc.c("premium")), 0, rowNum);
        add(new Label(insurance.getPremium() + ""), 1, rowNum++);

        add(new Label(Loc.c("amount")), 0, rowNum);
        add(new Label(insurance.getAmount() + ""), 1, rowNum++);

        add(new Label(Loc.c("deductible")), 0, rowNum);
        add(new Label(insurance.getDeductible() + ""), 1, rowNum++);

        add(new Label(Loc.c("description")), 0, rowNum);
        add(new Label(insurance.getDesc()), 1, rowNum++);

        add(new Label(Loc.c("status")), 0, rowNum);
        add(new Label(insurance.getStatus().getValue()), 1, rowNum++);

        add(new Separator(), 0, rowNum++, 2, 1);
    }

    protected void addClaimsTable()
    {
        getNode().add(new Label(Loc.c("claims")), 0, rowNum++);
        getNode().add(TableController.getClaimsTable(getInsurance().getClaims().stream())
                .getTable(), 0, rowNum++, 2, 1);
    }

    public T getInsurance() { return insurance; }

    public StandardGridPane getNode() { return this; }
}