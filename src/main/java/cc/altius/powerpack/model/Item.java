package cc.altius.powerpack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item implements Serializable {
    private int itemId;
    private int level;
    private String code;
    private String itemDescription;
    private Integer nextLevel;
    private String uiLabel;
}