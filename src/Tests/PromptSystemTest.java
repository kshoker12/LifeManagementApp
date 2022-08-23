package Tests;

import Model.Log;
import Model.PromptsSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromptSystemTest {
    private PromptsSystem promptSystem;

    @BeforeEach
    void init() {
        promptSystem = new PromptsSystem("Test");
        promptSystem.addPromptQuestion("What is your name?");
        promptSystem.initDays();
    }

    @Test
    void testGetFirstIncomplete() {
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Monday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(0).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(0).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Tuesday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(1).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(1).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Wednesday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(2).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(2).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Thursday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(3).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(3).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Friday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(4).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(4).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Saturday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(5).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(5).complete();
        assertEquals(promptSystem.getFirstIncomplete(), new Log("Sunday", promptSystem.getPromptQuestions()));
        promptSystem.getLogList().get(6).getPromptList().get(0).addAnswer("stop");
        promptSystem.getLogList().get(6).complete();
    }

}
