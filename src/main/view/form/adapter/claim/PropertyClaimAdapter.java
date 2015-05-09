package main.view.form.adapter.claim;

import main.model.claim.property.PropertyClaim;
import main.model.insurance.Insurance;
import main.model.person.Person;
import main.view.form.Formable;
import main.view.form.node.FormNode;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alex on 5/7/15.
 */
public class PropertyClaimAdapter extends ClaimAdapter<PropertyClaim> implements Formable<PropertyClaim>
{
    private PropertyClaim claim;

    public PropertyClaimAdapter(PropertyClaim claim)
    {
        super(claim);
        this.claim = claim;
    }

    public PropertyClaimAdapter(Person person, Insurance insurance)
    {
        super(person, insurance);
    }

    @Override
    public List<FormNode> getVisibleNodes()
    {
        List<FormNode> tmp = super.getVisibleNodes();
        return tmp;
    }

    @Override
    public void callback()
    {
        if (editMode) {
        } else {
        }

    }

    @Override
    public void setOnDoneAction(Consumer<PropertyClaim> c)
    {
        callBackEvent.setOnAction(e -> c.accept(claim));
    }
}
