import java.util.Objects;

public class Message {
    private String userName;
    private String messageContent;
    private String timeAgo;
    private long timestamp;

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public long getTimestamp() {
        return timestamp;
    }
    public String viewMessage(){ return this.userName + " - " + this.messageContent +" (" + this.timeAgo +")";};

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timestamp == message.timestamp && Objects.equals(messageContent, message.messageContent) && Objects.equals(userName, message.userName) && Objects.equals(timeAgo, message.timeAgo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageContent, userName, timestamp, timeAgo);
    }

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", timeAgo='" + timeAgo + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }


}
