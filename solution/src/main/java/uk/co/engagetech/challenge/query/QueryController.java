package uk.co.engagetech.challenge.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/vat")
    public Object fetchVat(@RequestParam("amount") String amount) {
        return ResponseEntity.ok(queryService.fetchVatFromAmount(amount));
    }
}
