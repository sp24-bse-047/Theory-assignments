import java.time.LocalDateTime;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private String messageId;
    private boolean status;
    private LocalDateTime timestamp;
    private boolean seen;

    private static int counter=0;

    public Message(String sender, String receiver, String content, boolean status,boolean seen) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.status = status;
        timestamp = LocalDateTime.now();
        this.messageId=String.format("%03d",++counter);
        this.seen=seen;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {

        return receiver;
    }

    public void setReceiver(String receiver) {

        this.receiver = receiver;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {

        this.messageId = messageId;
    }

    public boolean getStatus() {

        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {


        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public void setSeen(boolean seen) {

        this.seen = seen;
    }

    public boolean isSeen() {
        return seen;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", messageId='" + messageId + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", seen=" + seen +
                '}';
    }
}