package ro.iasi.fiipractic.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Reply {

    private String author;

    private String reply;

    private boolean privacy;


}
