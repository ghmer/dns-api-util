package link.parzival.util.api.dns;

/**
 * An object used to generate a querystring for certain
 * api-requests regarding dns records
 * 
 * @author Mario Ragucci
 *
 */
public class RecordsQueryParams {
    private int page;
    private int perPage;
    private String zoneId;
    
    private RecordsQueryParams(Builder builder) {
        this.page       = builder.page;
        this.perPage    = builder.items;
        this.zoneId     = builder.zone;
    }
    
    public static class Builder {
        private int page      = -1;
        private int items     = -1;
        private String zone   = null;
        
        public Builder page(int page) {
            this.page = page;
            return this;
        }
        
        public Builder items(int items) {
            this.items = items;
            return this;
        }
        
        public Builder zone(String zone) {
            this.zone = zone;
            return this;
        }
        
        public RecordsQueryParams build() {
            return new RecordsQueryParams(this);
        }
        
    }
    
    /**
     * Returns the query parameters as a string
     * @return the parameter string
     */
    public String getQueryParameterString() {
        boolean isFirstParam  = true;
        StringBuilder builder = new StringBuilder();
        
        if(this.zoneId != null) {
            if(isFirstParam) {
                isFirstParam = false;
            } else {
                builder.append('&');
            }
            builder.append(String.format("%s=%s", "zone_id", zoneId));
        }
        
        if(this.page != -1) {
            if(isFirstParam) {
                isFirstParam = false;
            } else {
                builder.append('&');
            }
            builder.append(String.format("%s=%s", "page", page));
        }
        
        if(perPage  != -1) {
            if(isFirstParam) {
                isFirstParam = false;
            } else {
                builder.append('&');
            }
            builder.append(String.format("%s=%s", "per_page", perPage));
        }
        
        String queryParameters = builder.toString();
        
        return queryParameters;
    }
}