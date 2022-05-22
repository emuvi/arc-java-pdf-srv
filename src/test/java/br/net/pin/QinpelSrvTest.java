package br.net.pin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class QinpelSrvTest {
    @Test
    void constructed() {
        var constructed = new PDFSrv();
        assertNotNull(constructed, "command should be constructed");
    }
}
