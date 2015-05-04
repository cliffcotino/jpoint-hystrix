package nl.jpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class MarkdownResource {

    @RequestMapping("/markdown")
    @ResponseBody
    String home() {
        return "Markdown!";
    }

}
