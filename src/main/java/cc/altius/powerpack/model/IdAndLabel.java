package cc.altius.powerpack.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IdAndLabel {
    @EqualsAndHashCode.Include
    private String id;
    private String label;
}
