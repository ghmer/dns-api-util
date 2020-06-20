/**
 * 
 */
package link.parzival.util.api.dns;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.logging.Logger;
import java.net.http.HttpResponse;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import link.parzival.util.api.dns.PayloadGenerator.RecordPayload;
import link.parzival.util.api.dns.PayloadGenerator.ZonePayload;

import java.net.http.HttpClient.Version;

/**
 * @author Mario Ragucci
 *
 */
public class DnsApiUtil {  
    public  static final String _API_URL_RECORDS = "https://dns.hetzner.com/api/v1/records";
    
    public  static final String _API_URL_ZONES   = "https://dns.hetzner.com/api/v1/zones";
    
    public  static final Logger _LOGGER          = Logger.getLogger(DnsApiUtil.class.getName());
    
    private static HttpClient httpClient         = null;
    
    /**
     * 
     */
    public DnsApiUtil() {
        httpClient = HttpClient.newBuilder()
                               .version(Version.HTTP_2)
                               .build();
    }

    //Bulk Create Records
    public JsonObject bulkCreateRecords(char[] authApiToken, JsonObject payload) throws Exception {
        throw new Exception("not implemented, yet!");
    }
    
    //Bulk Update Records
    public JsonObject bulkUpdateRecords(char[] authApiToken, JsonObject payload) throws Exception {
        throw new Exception("not implemented, yet!");
    }
    
    /**
     * Creates a new record using the given jsonObject as a payload for the api-call
     * @param authApiToken the api-token to authenticate with
     * @param payload the RecordPayload containing all information about the record
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject createRecord(char[] authApiToken, RecordPayload payload) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(_API_URL_RECORDS);
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendPostRequest(authApiToken, uri, payload.jsonPayload());
        
        return result;
    }
    
    /**
     * Creates a new zone using the given jsonObject as a payload for the api-call
     * @param authApiToken the api-token to authenticate with
     * @param payload the ZonePayload containing all information about the zone
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject createZone(char[] authApiToken, ZonePayload payload) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(_API_URL_ZONES);
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendPostRequest(authApiToken, uri, payload.jsonPayload());
        
        return result;
    }
    
    /**
     * Deletes the record identified by the recordId
     * @param authApiToken the api-token to authenticate with
     * @param recordId the ID of the record to be deleted
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject deleteRecord(char[] authApiToken, String recordId) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_RECORDS, recordId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendDeleteRequest(authApiToken, uri);
        
        return result;
    }
    
    /**
     * deletes a zone accessible by the api-token and identified by the given zoneId
     * @param authApiToken the api-token to authenticate with
     * @param zoneId the ID of the zone to delete
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject deleteZone(char[] authApiToken, String zoneId) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_ZONES, zoneId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendDeleteRequest(authApiToken, uri);
        
        return result;
    }

    /**
     * Returns a particular record, identified by the recordId
     * @param authApiToken the api-token to authenticate with
     * @param recordId the ID of rhe record to retrieve
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject getRecord(char[] authApiToken, String recordId) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_RECORDS, recordId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendGetRequest(authApiToken, uri);
        
        return result;
    }

    /**
     * Returns all records accessible by the given api-token. The result
     * may be further trimmed down by setting query parameters
     * @param authApiToken the api-token to authenticate with
     * @param queryParams queryparameters to use
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject getRecords(char[] authApiToken, RecordsQueryParams queryParams) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s?%s", _API_URL_RECORDS, queryParams.getQueryParameterString()));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendGetRequest(authApiToken, uri);
        
        return result;
    }
    
    /**
     * @param authApiToken the api-token to authenticate with
     * @param zoneId the zone id to retrieve
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject getZone(char[] authApiToken, String zoneId) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_ZONES, zoneId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendGetRequest(authApiToken, uri);
        
        return result;
    }
    
    /**
     * Return all zones accessible by the given api-token. The result
     * may be further trimmed down by setting query parameters
     * @param authApiToken the api-token to authenticate with
     * @param queryParams query parameters to add to the query
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject getZones(char[] authApiToken, ZonesQueryParams queryParams) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s?%s", _API_URL_ZONES, queryParams.getQueryParameterString()));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get zones: " + e.getMessage());
        }
        
        result = sendGetRequest(authApiToken, uri);
        
        return result;
    }
    
    private JsonObject sendDeleteRequest(char[] authApiToken, URI uri) throws Exception {
        JsonObject jsonResult   = null;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(uri)
                                             .DELETE()
                                             .header("Content-Type",   "application/json")
                                             .header("Auth-Api-Token", String.valueOf(authApiToken))
                                             .build();
        
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            jsonResult = jsonReader.readObject();
            
        } catch (IOException | InterruptedException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
        return jsonResult;
    }
    
    private JsonObject sendGetRequest(char[] authApiToken, URI uri) throws Exception {
        JsonObject jsonResult   = null;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(uri)
                                             .GET()
                                             .header("Content-Type",   "application/json")
                                             .header("Auth-Api-Token", String.valueOf(authApiToken))
                                             .build();
        
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            jsonResult = jsonReader.readObject();
            
        } catch (IOException | InterruptedException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
        return jsonResult;
    }
    
    private JsonObject sendPostRequest(char[] authApiToken, URI uri, JsonObject payload) throws Exception {
        JsonObject jsonResult   = null;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(uri)
                                             .POST(BodyPublishers.ofString(payload.toString()))
                                             .header("Content-Type",   "application/json")
                                             .header("Auth-Api-Token", String.valueOf(authApiToken))
                                             .build();
        
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            jsonResult = jsonReader.readObject();
            
        } catch (IOException | InterruptedException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
        return jsonResult;
    }
    
    private JsonObject sendPutRequest(char[] authApiToken, URI uri, JsonObject payload) throws Exception {
        JsonObject jsonResult   = null;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(uri)
                                             .PUT(BodyPublishers.ofString(payload.toString()))
                                             .header("Content-Type",   "application/json")
                                             .header("Auth-Api-Token", String.valueOf(authApiToken))
                                             .build();
                
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            jsonResult = jsonReader.readObject();
            
        } catch (IOException | InterruptedException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
        return jsonResult;
    }
    
    /**
     * Updates an existing DNS record with the data supplied in the JSON payload
     * @param authApiToken the api-token to use for authentication
     * @param recordId the ID of the record being updated
     * @param payload the payload in JSON format contain the new values for the record
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject updateRecord(char[] authApiToken, String recordId, JsonObject payload) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_RECORDS, recordId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendPutRequest(authApiToken, uri, payload);
        
        return result;
    }

    /**
     * Updates an existing DNS zone with the data supplied in the JSON payload
     * @param authApiToken the api-token to use for authentication
     * @param zoneId the ID of the zone being updated
     * @param payload the payload in JSON format contain the new values for the zone
     * @return the response body which is a JSON object, according to the api specs
     * @throws Exception
     */
    public JsonObject updateZone(char[] authApiToken, String zoneId, JsonObject payload) throws Exception {
        JsonObject result = null;
        URI uri           = null;
        
        try {
            uri = new URI(String.format("%s/%s", _API_URL_ZONES, zoneId));
            
        } catch (URISyntaxException e) {
            _LOGGER.severe(e.getMessage());
            throw new Exception("Unable to get record: " + e.getMessage());
        }
        
        result = sendPutRequest(authApiToken, uri, payload);
        
        return result;
    }
}
