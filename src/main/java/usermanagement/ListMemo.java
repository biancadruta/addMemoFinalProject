package usermanagement;

import java.time.LocalDate;

public class ListMemo {
    private int id ;
    private String eventName;

    @Override
    public String toString() {
        return "ListMemo{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                '}';
    }

    public int getIduser(){return iduser;}

    public void setIduser(int iduser){this.iduser=iduser;}


    private LocalDate eventDate;
    private int iduser;

    public ListMemo(String eventName, LocalDate eventDate, int iduser){
        this.eventName= eventName;
        this.eventDate= eventDate;
        this.iduser= iduser;

    }
    public ListMemo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
