public class Puzzle {
    private int puzzleID;
    private int initRoomID;
    private int numbOfAttempts;
    private String question;
    private String answer;
    private String solvedMessage;

    public Puzzle(int puzzleID, int initRoomID, int numbOfAttempts, String question, String answer, String solvedMessage) {
        this.puzzleID = puzzleID;
        this.initRoomID = initRoomID;
        this.numbOfAttempts = numbOfAttempts;
        this.question = question;
        this.answer = answer;
        this.solvedMessage = solvedMessage;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public int getInitRoomID() {
        return initRoomID;
    }

    public int getNumbOfAttempts() {
        return numbOfAttempts;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSolvedMessage() {
        return solvedMessage;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleID=" + puzzleID +
                ", initRoomID=" + initRoomID +
                ", numbOfAttempts=" + numbOfAttempts +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", solvedMessage='" + solvedMessage + '\'' +
                '}';
    }
}
