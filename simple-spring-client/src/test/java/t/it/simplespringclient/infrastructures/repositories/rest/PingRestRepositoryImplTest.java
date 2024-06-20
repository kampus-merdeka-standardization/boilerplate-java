package t.it.simplespringclient.infrastructures.repositories.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.infrastructures.repositories.responses.MetaResponse;
import t.it.simplespringclient.infrastructures.repositories.responses.WebResponse;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(value = {MockitoExtension.class})
@SuppressWarnings("unchecked")
class PingRestRepositoryImplTest {
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @Mock
    private WebClient webClient;
    private PingRepository pingRepository;

    @BeforeEach
    void setUp() {
        pingRepository = new PingRestRepositoryImpl(webClient);
    }

    @Test
    void testInit() {
        assertNotNull(webClient);
        assertNotNull(requestHeadersUriSpec);
        assertNotNull(pingRepository);
    }

    @Test
    void testPing_Success() {
        WebResponse<String> webResponse = WebResponse.<String>builder().data("pong").meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build()).build();

        when(responseSpec.bodyToMono(new ParameterizedTypeReference<WebResponse<String>>() {
        })).thenReturn(Mono.just(webResponse));
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.accept(any(MediaType.class))).thenReturn(requestHeadersUriSpec);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        Flux<String> ping = pingRepository.ping();

        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(new ParameterizedTypeReference<WebResponse<String>>() {
        });
        verify(responseSpec, times(1)).onStatus(any(), any());
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestHeadersUriSpec, times(1)).uri(anyString());
        verify(requestHeadersUriSpec, times(1)).accept(any(MediaType.class));
        verify(webClient, times(1)).get();
    }

    @Test
    void testPing_Fail() {
        WebClientResponseException internalError = WebClientResponseException.InternalServerError.create(500, "internal error", HttpHeaders.EMPTY, new byte[]{}, Charset.defaultCharset());

        when(responseSpec.bodyToMono(new ParameterizedTypeReference<WebResponse<String>>() {
        })).thenReturn(Mono.error(internalError));
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.accept(any(MediaType.class))).thenReturn(requestHeadersUriSpec);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        Flux<String> ping = pingRepository.ping();

        StepVerifier.create(ping)
                .expectError()
                .verify();

        verify(responseSpec, times(1)).bodyToMono(new ParameterizedTypeReference<WebResponse<String>>() {
        });
        verify(responseSpec, times(1)).onStatus(any(), any());
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestHeadersUriSpec, times(1)).uri(anyString());
        verify(requestHeadersUriSpec, times(1)).accept(any(MediaType.class));
        verify(webClient, times(1)).get();
    }
}