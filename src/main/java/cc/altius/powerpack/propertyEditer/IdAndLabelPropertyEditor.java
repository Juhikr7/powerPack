package cc.altius.powerpack.propertyEditer;

import cc.altius.powerpack.model.IdAndLabel;
import java.beans.PropertyEditorSupport;

public class IdAndLabelPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new IdAndLabel(text, null));
    }

    @Override
    public String getAsText() {
        if (getValue() == null) {
            return null;
        } else {
            return ((IdAndLabel) getValue()).getId();
        }
    }

}
