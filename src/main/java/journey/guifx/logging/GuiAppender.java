package journey.guifx.logging;

import javafx.scene.control.TextArea;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;


public class GuiAppender extends WriterAppender {

    private static volatile TextArea textArea;

    public static void setTextArea(TextArea textArea) {
       GuiAppender.textArea = textArea;
    }

    @Override
    public void append(final LoggingEvent loggingEvent) {
        String message = this.layout.format(loggingEvent);
        if (textArea != null) {
            textArea.selectEnd();
            textArea.insertText(textArea.getText().length(), message);
        }
    }
}
