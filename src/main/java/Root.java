import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Root{
    public String error;
    public int processCode;
    public String description;
    public String responseDescription;
    public String responseCode;
    public List<FieldMap> fieldMap;
}