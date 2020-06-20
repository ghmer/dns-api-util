package link.parzival.util.api.dns;

public class ZonesQueryParams {
    private int page            = -1;
    private int perPage         = -1;
    private String searchName   = null;
    private String zoneId       = null;
         
    private ZonesQueryParams(Builder builder) {
        this.page = builder.page;
        this.perPage = builder.items;
        this.searchName = builder.search;
        this.zoneId = builder.zone;
    }
    
    public static class Builder {
        private int page        = -1;
        private int items       = -1;
        private String zone     = null;
        private String search   = null;
        
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
        
        public Builder search(String search) {
            this.search = search;
            return this;
        }
        
        public ZonesQueryParams build() {
            return new ZonesQueryParams(this);
        }
        
    }
    
    public String getQueryParameterString() {
        boolean isFirstParam  = true;
        StringBuilder builder = new StringBuilder();
        
        if(this.searchName != null) {
            if(isFirstParam) {
                isFirstParam = false;
            } else {
                builder.append('&');
            }
            builder.append(String.format("%s=%s", "search_name", searchName));
        }
        
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
