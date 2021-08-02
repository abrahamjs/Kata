import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String userId;
    private String userName;
    private TimeLine userTimeline;
    private List<User> followers;
    private List<User> following;
    private Wall userWall;

    public User(String userId, String userName, TimeLine userTimeline, List<User> followers, List<User> following, Wall userWall) {
        this.userId = userId;
        this.userName = userName;
        this.userTimeline = userTimeline;
        this.followers = followers;
        this.following = following;
        this.userWall = userWall;
    }

    public User() {
        this.userId = "";
        this.userName = "";
        this.userTimeline = new TimeLine();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        this.userWall = new Wall();
    }

    public TimeLine getUserTimeline() {
        return userTimeline;
    }
    public void followUser(User user){
        //Update list of followers
        List<User> followers = this.followers;
        followers.add(user);
        //Update user's wall with user 2 messages
        this.getUserWall().aggregateUser(user);
        //Update list of followers of user 2
        List<User> updatedFollowers = user.getFollowers();
        updatedFollowers.add(this);
        user.setFollowers(updatedFollowers);
    }

    public void publishMessage(Message message){
        this.userTimeline.addMessage(message);
        this.userWall.addUserMessage(message);
        //Propagate new message for each of the user's followers wall
        this.followers.forEach(follower -> {
            follower.getUserWall().addUserMessage(message);
        });
    }

    public void setUserTimeline(TimeLine userTimeline) {
        this.userTimeline = userTimeline;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Wall getUserWall() {
        return userWall;
    }

    public void setUserWall(Wall userWall) {
        this.userWall = userWall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userTimeline, user.userTimeline) && Objects.equals(followers, user.followers) && Objects.equals(following, user.following) && Objects.equals(userWall, user.userWall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userTimeline, followers, following, userWall);
    }
}
