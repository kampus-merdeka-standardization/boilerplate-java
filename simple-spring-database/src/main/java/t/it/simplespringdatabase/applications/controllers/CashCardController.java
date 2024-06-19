package t.it.simplespringdatabase.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.applications.payloads.responses.MetaResponse;
import t.it.simplespringdatabase.applications.payloads.responses.WebResponse;
import t.it.simplespringdatabase.domains.services.CashCardService;
import t.it.simplespringdatabase.domains.services.models.*;

@RestController
@RequiredArgsConstructor
public class CashCardController {
    private final CashCardService cashCardService;

    @PostMapping(path = "/cashcards", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WebResponse<PersistedCashCard>> addProduct(@RequestBody Mono<AddCashCard> addCashCard) {
        return cashCardService.addCashCard(addCashCard).map(persistedCashCard ->
                WebResponse.<PersistedCashCard>builder()
                        .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.CREATED.value())).message(HttpStatus.CREATED.getReasonPhrase()).build()).data(persistedCashCard).build());
    }

    @GetMapping(path = "cashcards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedCashCardDetail>> getProduct(@PathVariable("id") String id) {
        return cashCardService.getCashCardById(id).map(cashCardDetail -> WebResponse.<PersistedCashCardDetail>builder()
                .data(cashCardDetail)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @PutMapping(path = "/cashcards/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedCashCard>> updateAllProductFields(@PathVariable("id") String id, @RequestBody Mono<UpdateCashCard> updateCashCard) {
        return cashCardService.updateCashCard(updateCashCard.map(item -> item.withId(id))).map(persistedCashCard -> WebResponse.<PersistedCashCard>builder()
                .data(persistedCashCard)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @DeleteMapping(path = "cashcards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<String>> deleteProductById(@PathVariable("id") String id) {
        return cashCardService.deleteCashCardById(id).map(deletedId -> WebResponse.<String>builder()
                .data(deletedId)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }
}
