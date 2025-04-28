package ijse.lk.RestDemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    //show initial html form
    @RequestMapping("/showform")
    public String showForm(){
        //Resolves to src/main/resources/templates/helloworld-form.html (Thymeleaf template)
        //Shows an initial HTML form to the user
        return "helloworld-form";

    }
    //method to process the Html form

    @RequestMapping("/processForm")
   public String processForm(){
        //Resolves to helloworld.html template
        //Displays a response page after form processing
        return "helloworld";
   }

}
