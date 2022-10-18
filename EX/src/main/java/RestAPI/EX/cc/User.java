package RestAPI.EX.cc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private Date date;

}
