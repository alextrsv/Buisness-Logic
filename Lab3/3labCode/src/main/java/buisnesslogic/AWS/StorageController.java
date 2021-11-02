package buisnesslogic.AWS;


import buisnesslogic.jms.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping()
public class StorageController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping("/avatar")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile avatar) {
        jmsProducer.sendMessage(avatar);
        return new ResponseEntity<String>("avatar's downloading", HttpStatus.OK);
    }

}
