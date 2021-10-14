import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FieldMap {
    public int fieldID;
    public String fieldName;
    public Object value;
}