package jnaalisv.kona.web.interfaces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PingControllerTest {

    @Test
    public void pingShouldReturnTimeStampInMillis() {
        long previousTimeStamp = System.currentTimeMillis();
        assertThat(new PingController().ping()).isGreaterThan(previousTimeStamp);
    }

}
