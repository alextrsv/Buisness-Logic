package buisnesslogic.jms;


import buisnesslogic.jms.fileWork.FileAsByteArrayManager;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import java.io.File;
import java.io.IOException;

@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;


    private FileAsByteArrayManager fileManager = new FileAsByteArrayManager();

    @Value("${active-mq.queue}")
    private String queue;

    public void sendMessage(MultipartFile receivedFile){
        try{
            System.out.println("Attempting Send message to queue: "+ queue);

            File convertedFile = fileManager.convertMultiPartFileToFile(receivedFile);

            sendFileAsBytesMessage(convertedFile);

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    private void sendFileAsBytesMessage(File file) throws JMSException, IOException {
        ActiveMQBytesMessage bytesMessage = new ActiveMQBytesMessage();

        bytesMessage.setStringProperty("fileName", file.getName());
        bytesMessage.setStringProperty("userName", getUserName());

        bytesMessage.writeBytes(fileManager.fileToBytes(file));

        jmsTemplate.convertAndSend(queue, bytesMessage);
    }

    private String getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
