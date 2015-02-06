package fr.unice.polytech.directace.acessor;

/**
 * Created by clement0210 on 15-02-06.
 */
public class MatchLog {
    private String id, eventName, value, date, playerID, matchID;

    public MatchLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    @Override
    public String toString() {
        return "MatchLog{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", value='" + value + '\'' +
                ", date='" + date + '\'' +
                ", playerID='" + playerID + '\'' +
                ", matchID='" + matchID + '\'' +
                '}';
    }
}
