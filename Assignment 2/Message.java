import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Comparable<Message> {
    private String message;
    private String id;
    private LocalDateTime timestamp;
    private static int counter = 0;
    private boolean seen;

    public Message(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.id=String.format("%03d", ++counter);
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "Message [message=" + message + ", id=" + id + ", timestamp=" + timestamp + "]";
    }
    @Override
    public int compareTo(Message o) {
        return timestamp.compareTo(o.getTimestamp());
    }

}
