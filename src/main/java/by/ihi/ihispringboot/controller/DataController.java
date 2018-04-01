package by.ihi.ihispringboot.controller;

import by.ihi.ihispringboot.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

@Controller
public class DataController extends ExceptionHandlerController {

//    private static final Logger LOG = Logger.getLogger(DataController.class);
    private static final Logger LOG = LoggerFactory.getLogger(DataController.class);

    @Autowired
    @Qualifier("dataService")
    private DataService dataService;

    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> persist(@RequestParam("data") String data) throws RestException {
        try {
            if (data == null || data.equals("")) {
                return Ajax.emptyResponse();
            }
            dataService.persist(data);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/getRandomData", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getRandomData() throws RestException {
        try {
            Set<String> result = dataService.getRandomData();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}
