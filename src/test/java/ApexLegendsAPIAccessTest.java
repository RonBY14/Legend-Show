import com.legends.api.ApexLegendsAPIAccess;
import com.legends.data.player.PlayerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApexLegendsAPIAccessTest {

    private final String NAME = "HeyImLifeline";
    private final String PLATFORM = "PC";
    private final String API_KEY = "RkXTtZwNa2wEqKMl9ndA";

    @Test
    public void getPlayerTest() {
        PlayerData playerData = ApexLegendsAPIAccess.getPlayer(NAME, PLATFORM, API_KEY);
        Assertions.assertNotNull(playerData, "Test Player Wasn't Found!");
        Assertions.assertEquals(NAME.toLowerCase(), playerData.getName().toLowerCase(),
                        "Test player and retrieved player names are not the same");
    }

}
