package entity;

public class Answer {
    private int id;
    private int mentorcoderequest;
    private String content;

    public Answer() {
    }

    public Answer(int id, int mentorcoderequest, String content) {
        this.id = id;
        this.mentorcoderequest = mentorcoderequest;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMentorcoderequest() {
        return mentorcoderequest;
    }

    public void setMentorcoderequest(int mentorcoderequest) {
        this.mentorcoderequest = mentorcoderequest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
