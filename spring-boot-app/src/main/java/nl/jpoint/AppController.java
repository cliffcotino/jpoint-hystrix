package nl.jpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class AppController {

    @Autowired
    private AppServiceAdapter adapter;

    @RequestMapping("/markdown")
    @ResponseBody
    public String home() {
        return adapter.execute();
    }

}
