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
        return "{" +
                "\"id\" :'" + id + '\'' +
                ", \"eventName\" :'" + eventName + '\'' +
                ", \"value\" :'" + value + '\'' +
                ", \"date\" :'" + date + '\'' +
                ", \"playerID\" :'" + playerID + '\'' +
                ", \"matchID\" :'" + matchID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchLog matchLog = (MatchLog) o;

        if (date != null ? !date.equals(matchLog.date) : matchLog.date != null) return false;
        if (eventName != null ? !eventName.equals(matchLog.eventName) : matchLog.eventName != null) return false;
        if (id != null ? !id.equals(matchLog.id) : matchLog.id != null) return false;
        if (matchID != null ? !matchID.equals(matchLog.matchID) : matchLog.matchID != null) return false;
        if (playerID != null ? !playerID.equals(matchLog.playerID) : matchLog.playerID != null) return false;
        if (value != null ? !value.equals(matchLog.value) : matchLog.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (playerID != null ? playerID.hashCode() : 0);
        result = 31 * result + (matchID != null ? matchID.hashCode() : 0);
        return result;
    }

}
