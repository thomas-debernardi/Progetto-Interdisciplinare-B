package Database;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MatchesDTO {
    private String id;
    private String date;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MatchesDTO(String id, LocalDateTime localTime) {
        this.id = id;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        date = dtf.format(localTime);
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = dtf.format(localTime);
    }

    public MatchesDTO(){}
}
