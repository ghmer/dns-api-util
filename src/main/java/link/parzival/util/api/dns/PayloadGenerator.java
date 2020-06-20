package link.parzival.util.api.dns;

import javax.json.Json;
import javax.json.JsonObject;

public class PayloadGenerator {

    public class RecordPayload {
        RecordType recordType;
        String zoneId;
        String name;
        String value;
        int ttl;
        private RecordPayload(String zoneId, RecordType recordType, String name, String value, int ttl) {
            this.zoneId = zoneId;
            this.recordType = recordType;
            this.name = name;
            this.value = value;
            this.ttl = ttl;
        }
        
        public JsonObject jsonPayload() {
            JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("zone_id", this.zoneId)
                                    .add("type", this.recordType.name())
                                    .add("name", this.name)
                                    .add("value", this.value)
                                    .add("ttl", this.ttl)
                                    .build();
            
            return jsonObject;
        }
    }
    
    public class ZonePayload {
        String name;
        int ttl;
        private ZonePayload(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }
        
        public JsonObject jsonPayload() {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("name", this.name)
                    .add("ttl", this.ttl)
                    .build();

            return jsonObject;
        }
    }

    public RecordPayload buildRecordPayload(String zoneId, RecordType recordType, String name, String value, int ttl) {
        RecordPayload payload = new RecordPayload(zoneId, recordType, name, value, ttl);
        
        return payload;
    }
    
    public ZonePayload buildZonePayload(String name, int ttl) {
        ZonePayload payload = new ZonePayload(name, ttl);
        
        return payload;
    }
}
