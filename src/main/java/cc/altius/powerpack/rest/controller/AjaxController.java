package cc.altius.powerpack.rest.controller;

import cc.altius.powerpack.service.ManageFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {

    @Autowired
    private ManageFlowService manageFlowService;

    @PostMapping("/addFlow.htm")
    public ResponseEntity addFlow(@RequestParam(value = "code", required = true) String code,
                                          @RequestParam(value = "itemDesc", required = true) String itemDesc,
                                          @RequestParam(value = "level", required = true) int level,
                                          @RequestParam(value = "nextLevel", required = false) int nextLevel,
                                          @RequestParam(value = "uiLabel", required = false) String uiLabel,
                                          @RequestParam(value = "lastLevel", required = false) boolean isLastLevel){
        try {
            manageFlowService.addFlow(code,itemDesc,level,nextLevel,uiLabel,isLastLevel);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
