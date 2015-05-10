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
    protected boolean drawn = false;

    public InsuranceView(T insurance)
    {
        this.insurance = insurance;
        draw();
    }

    private void draw()
    {
        if (drawn) {
            getChildren().clear();
            rowNum = 0;
        }

        initButtonPanel();
        initInsuranceFields();
        childDraw();
        drawn = true;
    }

    private void initButtonPanel()
    {
        ToolBar buttonPane = new ToolBar();

        Button b1 = new Button(Loc.c("new_claim"));
        switch (insurance.identify()) {
            case CAR:
                b1.setOnAction(e -> ClaimController.create(Claim.ClaimType.CAR,
                        insurance.getCustomer(), insurance));
                break;
            case BOAT:
                b1.setOnAction(e -> ClaimController.create(Claim.ClaimType.BOAT,
                        insurance.getCustomer(), insurance));
                break;
            case VACATION_HOUSE:
            case HOUSE:
                b1.setOnAction(e -> ClaimController.create(Claim.ClaimType.PROPERTY,
                        insurance.getCustomer(), insurance));
                break;
            case TRAVEL:
                b1.setOnAction(e -> ClaimController.create(Claim.ClaimType.TRAVEL,
                        insurance.getCustomer(), insurance));
                break;
        }

        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> InsuranceController.edit(insurance));

        Button refreshButton = new Button(Loc.c("refresh"));
        refreshButton.setOnAction(e -> draw());

        buttonPane.getItems().addAll(b1, editButton, refreshButton);

        add(buttonPane, 0, rowNum++, 2, 1);
    }


    private void initInsuranceFields()
    {
        add(new Label(Loc.c("insuranceId")), 0, rowNum);
        add(new Label(insurance.getId() + ""), 1, rowNum++);

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

    protected abstract void childDraw();

    @Override
    public StandardGridPane getNode() { return this; }
}
