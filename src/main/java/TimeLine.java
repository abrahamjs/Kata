import org.ocpsoft.prettytime.PrettyTime;

import java.util.*;

public class TimeLine {
    private List<Message> timeLineMessages;

    public TimeLine(List<Message> timeLineMessages) {
        this.timeLineMessages = timeLineMessages;
    }
    public TimeLine() {
        this.timeLineMessages = new ArrayList<>();
    }

    public List<Message> getTimeLineMessages(){
        return timeLineMessages;
    }
    //Sets time ago property with the help of the PrettyTime library and returns updated timeline
    public List<Message> getTimeLineMessagesWithTimeAgo(){
        PrettyTime p = new PrettyTime();
        List<Message> timeline = new ArrayList<>(this.timeLineMessages);
        Collections.reverse(timeline);
        for(Message message: timeline){
            message.setTimeAgo(p.format(new Date(message.getTimestamp())));
        }
        return timeline;
    }
    public Message addMessage(Message message){
        this.timeLineMessages.add(message);
        return message;
    }


    public void setTimeLineMessages(List<Message> timeLineMessages) {
        this.timeLineMessages = timeLineMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLine timeLine = (TimeLine) o;
        return Objects.equals(timeLineMessages, timeLine.timeLineMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeLineMessages);
    }

}
