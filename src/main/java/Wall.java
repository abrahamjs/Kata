import org.ocpsoft.prettytime.PrettyTime;

import java.util.*;
import java.util.stream.Collectors;

public class Wall {
    private List<Message> wall;

    public Wall(List<Message> wall) {
        this.wall = wall;
    }

    public Wall() {
        this.wall = new ArrayList<>();
    }

    public List<Message> retrieveWallWithTimeAgo(){
        PrettyTime p = new PrettyTime();
        List<Message> messagesInWall = this.wall;
        messagesInWall = messagesInWall.stream()
                .sorted(Comparator.comparingLong(Message::getTimestamp))
                .collect(Collectors.toList());
        Collections.reverse(messagesInWall);

        messagesInWall.forEach(message -> {
            message.setTimeAgo(p.format(new Date(message.getTimestamp())));});

        return messagesInWall;
    }


    public List<Message> getWall() {
        return wall;
    }
    public void addUserMessage(Message message){
        this.wall.add(message);
    }
    public void  aggregateUser(User user){
        List<Message> userTimeline = user.getUserTimeline().getTimeLineMessages();
        this.wall.addAll(userTimeline);
    }

    public void setWall(List<Message> wall) {
        this.wall = wall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall1 = (Wall) o;
        return Objects.equals(wall, wall1.wall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wall);
    }
}
