package de.robinschuerer.norcom.searchservice;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    private static final String URL = "http://localhost:9200/enron/data/_search";

    private RestTemplate restTemplate = new RestTemplate();

    @Nonnull
    public ArrayNode search(@Nonnull final String term) {

        final ObjectNode result = this.restTemplate.postForObject(
            URL,
            getHttpEntity(newSearchSpec(term)),
            ObjectNode.class);

        LOGGER.info(result.toString());

        final JsonNode hitsNode = result.get("hits");

        return (ArrayNode) hitsNode.get("hits");
    }

    @Nonnull
    private ObjectNode newSearchSpec(@Nonnull final String term) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectNode rootNode = objectMapper.createObjectNode();

        final ObjectNode queryNode = rootNode.putObject("query");
        final ObjectNode termNode = queryNode.putObject("term");
        termNode.put("sender", term);

        LOGGER.info(rootNode.toString());
        return rootNode;
    }

    private static <T> HttpEntity<T> getHttpEntity(final T mapping) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(mapping, headers);
    }

}
