package buisnesslogic.jms;


import buisnesslogic.AWS.StorageService;
import buisnesslogic.jms.fileWork.FileAsByteArrayManager;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.File;
import java.io.IOException;

@Component
public class JmsConsumer implements MessageListener {


    private FileAsByteArrayManager fileManager = new FileAsByteArrayManager();


    @Autowired
    StorageService storageService;

    @Override
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(Message message) {
        try {
            System.out.println("============CONSUMER JOB...==========");
            String filename = message.getStringProperty("fileName");
            String userName = message.getStringProperty("userName");

            if (message instanceof ActiveMQBytesMessage) {
                handleBytesMessage((ActiveMQBytesMessage) message, filename, userName);
            } else {
                System.out.println("test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleBytesMessage(ActiveMQBytesMessage bytesMessage, String filename, String userName)
            throws IOException, JMSException {
        String outputfileName = filename;

        File file = fileManager.bytesTOFile(bytesMessage.getContent().getData(), outputfileName);

        storageService.uploadAvatar(file, userName);
    }


}
