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
        this.id = String.format("%03d", ++counter);
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
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
