package org.openapitools.configuration;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Home redirection to OpenAPI api documentation
 */
@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(@RequestHeader(value = "x-original-uri", required = false) Optional<String> xOriginalUri) {
        if (xOriginalUri.isPresent() && !xOriginalUri.get().isEmpty()) {
            String origWithoutTrailingSlash = xOriginalUri.get();
            if (origWithoutTrailingSlash.charAt(origWithoutTrailingSlash.length() - 1) == '/') {
                origWithoutTrailingSlash = origWithoutTrailingSlash.substring(0, origWithoutTrailingSlash.length() - 1);
            }
            return String.format("redirect:%s/swagger-ui.html", origWithoutTrailingSlash);
        }
        
        return "redirect:swagger-ui.html";
    }


}
