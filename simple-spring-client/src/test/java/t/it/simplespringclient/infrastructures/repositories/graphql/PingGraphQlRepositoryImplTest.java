package t.it.simplespringclient.infrastructures.repositories.graphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.graphql.GraphQlRequest;
import org.springframework.graphql.client.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.PingRepository;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(value = {MockitoExtension.class})
class PingGraphQlRepositoryImplTest {
    @Mock
    private GraphQlClient.RequestSpec requestSpec;
    @Mock
    private GraphQlClient.RetrieveSpec retrieveSpec;
    @Mock
    private HttpGraphQlClient httpGraphQlClient;
    @Mock
    private GraphQlRequest graphQlRequest;
    private PingRepository pingRepository;


    @BeforeEach
    void setUp() {
        pingRepository = new PingGraphQlRepositoryImpl(httpGraphQlClient);
    }

    @Test
    void testInit() {
        assertNotNull(pingRepository);
        assertNotNull(requestSpec);
        assertNotNull(retrieveSpec);
    }

    @Test
    void testPing_Success() {
        Mono<String> pongResponse = Mono.just("pong");

        when(retrieveSpec.toEntity(String.class)).thenReturn(pongResponse);
        when(httpGraphQlClient.documentName(anyString())).thenReturn(requestSpec);
        when(requestSpec.retrieve(anyString())).thenReturn(retrieveSpec);

        Flux<String> ping = pingRepository.ping();

        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();

        verify(retrieveSpec, times(1)).toEntity(String.class);
        verify(httpGraphQlClient, times(1)).documentName(anyString());
        verify(requestSpec, times(1)).retrieve(anyString());
    }

    @Test
    void testPing_Fail() {
        when(retrieveSpec.toEntity(String.class)).thenReturn(Mono.error(new GraphQlClientException("Failed to access the message field", null, graphQlRequest)));
        when(httpGraphQlClient.documentName(anyString())).thenReturn(requestSpec);
        when(requestSpec.retrieve(anyString())).thenReturn(retrieveSpec);

        Flux<String> ping = pingRepository.ping();

        StepVerifier.create(ping)
                .expectError()
                .verify();

        verify(retrieveSpec, times(1)).toEntity(String.class);
        verify(httpGraphQlClient, times(1)).documentName(anyString());
        verify(requestSpec, times(1)).retrieve(anyString());
    }
}