package t.it.simplespringclient.infrastructures.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.applications.models.responses.PongResponse;

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
    void testPing() {

        Mono<PongResponse> pongResponse = Mono.just(PongResponse.builder().message("pong").build());

        when(retrieveSpec.toEntity(PongResponse.class)).thenReturn(pongResponse);
        when(httpGraphQlClient.documentName(anyString())).thenReturn(requestSpec);
        when(requestSpec.retrieve(anyString())).thenReturn(retrieveSpec);

        Mono<String> ping = pingRepository.ping();

        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();

        verify(retrieveSpec, times(1)).toEntity(PongResponse.class);
        verify(httpGraphQlClient, times(1)).documentName(anyString());
        verify(requestSpec, times(1)).retrieve(anyString());
    }
}