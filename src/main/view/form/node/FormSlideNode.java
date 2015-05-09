package main.view.form.node;

import javafx.scene.control.*;
import javafx.scene.paint.Color;

import javax.swing.text.LabelView;

/**
 * Simple FormNode class used for structuring data
 * and sending a key-value pair to the Form class.
 */
public class FormSlideNode extends FormNode<Integer>
{
    private String key;
    private int min, max, value;
    private Label keyLabel;
    private Slider valueSlider;

    public static class Builder
    {
        private String key;
        private int min, max, value;

        public Builder(String key, int min, int max)
        {
            this.key = key;
            this.min = min;
            this.max = max;
            this.value = (max - min) / 2;
        }

        public Builder value(int value)
        {
            this.value = value;
            return this;
        }

        public FormSlideNode build()
        {
            return new FormSlideNode(this);
        }
    }

    private FormSlideNode(Builder builder)
    {
        this.key = builder.key;
        this.min = builder.min;
        this.max = builder.max;
        this.value = builder.value;

        keyLabel = new Label(key + ":");

        valueSlider = new Slider();
        valueSlider.setMin(min);
        valueSlider.setMax(max);
        valueSlider.setValue(value);
        valueSlider.setShowTickLabels(true);
        valueSlider.setShowTickMarks(true);
        valueSlider.setSnapToTicks(true);
    }

    public Label getKey() { return keyLabel; }

    public Slider getNode() { return valueSlider; }

    public Label getError() { return new Label(""); }

    public String getValue() { return Double.toString(valueSlider.getValue()); }

    public Integer getData() { return (int)valueSlider.getValue(); }

    @Override
    public Type getType() { return Type.SLIDER; }

}
