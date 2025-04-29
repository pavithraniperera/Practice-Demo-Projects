package ijse.lk.RestDemo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
   //need controller method to read form data and add data to the model
   @RequestMapping("/processFormVersionTwo")
   public String letsShoutDude(HttpServletRequest request, Model model) {

       // read the request parameter from the HTML form
       String theName = request.getParameter("studentName");

       // convert the data to all caps
       theName = theName.toUpperCase();

       // create the message
       String result = "Yo! " + theName;

       // add message to the model
       model.addAttribute("message", result);

       return "helloworld";
   }
    @RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model model) {

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey My Friend from v3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

}
